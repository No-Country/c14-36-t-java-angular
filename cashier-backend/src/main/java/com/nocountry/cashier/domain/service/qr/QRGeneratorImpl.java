package com.nocountry.cashier.domain.service.qr;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.nocountry.cashier.domain.usecase.qr.QRGeneratorService;
import com.nocountry.cashier.exception.GenericException;
import com.nocountry.cashier.util.ImageUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author ROMULO
 * @package com.lrpa.app.domain.service.email
 * @license Lrpa, zephyr cygnus
 * @since 19/9/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QRGeneratorImpl implements QRGeneratorService {

    private final ImageUtils imageUtils;

    @Value("${path.directory.qr}")
    private String pathQr;

    @PostConstruct
    public void init() throws IOException {
        Path rootLocation = Paths.get(pathQr);
        Files.createDirectories(rootLocation);
    }

    /**
     * Aquí sucede la magia de generar el QR, PERSONALIZARLO, SUPERPONER LOGOS, DARLE COLOR.
     *
     * @param text   Lo que desees encriptar en el qr String
     * @param width  int
     * @param height int
     * @return ResponseEntity<Resource>
     * @throws WriterException Exceptio propia de la libreria zxing
     */
    @Override
    public ResponseEntity<Resource> generateQrCodeImage(String text, int width, int height, String filename) throws WriterException {


        //? lo que hacemos es reducir el margen desde adentro hacia afuera
        int qrWidth = width - 2 * 40;
        int qrHeight = height - 2 * 40;

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> encodeHintType = new EnumMap<>(EncodeHintType.class);
        encodeHintType.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        encodeHintType.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        // ? creamos el qr ,pasando el texto, que tipo de formato de codigo va a tener, ancho ,altura, UTF8
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, qrWidth, qrHeight, encodeHintType);

        // * generamos el qr en memoria para luego ser tratada en tiempo de ejecucióm
        BufferedImage qrImage = imageUtils.toCustomizeQR(bitMatrix);

        Path rutaArchivo = setPathImage(filename, pathQr);
        // * try with reosurce para liberar recursos, se escribe la imagen en el buffer de la memoria, aun no se persiste
        try {
            // * persistimos con esta función para guardarla en local
            // * Sobreescribe la imagen si ya existe
            ImageIO.write(qrImage, "png", new File(rutaArchivo.toUri()));
            log.info("QR GENERADO CORRECTAMENTE");

            // ? Cargamos la imagen de mi local y lo exponemos al cliente
            return uploadLocalImage(filename);
        } catch (IOException e) {
            log.info("Hubo un problema al guardar el Qr en local");
            throw new GenericException("Failed to write QR code image to output stream.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * ? Extrae la imagen generada osea el qr y lo muestra al usuario
     *
     * @param filename nombre de la imagen+formato
     * @return ResponseEntity<Resource>
     */
    @Override
    public ResponseEntity<Resource> uploadLocalImage(String filename) {
        var ruta = setPathImage(filename, pathQr);
        File file = new File(ruta.toUri());
        Resource resource;
        try {
            if (!file.exists()) return ResponseEntity.notFound().build();
            // ? examina y trae mi recurso en local -> MIENTRAS QUE new UrlResource("https://www.images.maipevi.png") analiza recursos externos
            resource = new FileSystemResource(file);
            log.info("Retornando el recurso la imagen con el Qr code");
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)                   //attachment; filename=
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                    .body(resource);

        } catch (Exception e) {
            throw new GenericException("Error al cargar la imagen del local ->" + e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
