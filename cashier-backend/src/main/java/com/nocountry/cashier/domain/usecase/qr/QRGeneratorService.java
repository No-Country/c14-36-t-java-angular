package com.nocountry.cashier.domain.usecase.qr;

import com.google.zxing.WriterException;
import com.nocountry.cashier.exception.GenericException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ROMULO
 * @package com.lrpa.app.domain.service.qr
 * @license Lrpa, zephyr cygnus
 * @since 19/9/2023
 */
public interface QRGeneratorService{
    ResponseEntity<Resource> generateQrCodeImage(String text, int width, int height,String filename) throws WriterException;

    ResponseEntity<Resource> uploadLocalImage(String filename);

    default Path setPathImage(String filename, String path) {
        return Paths.get(path).resolve(filename).toAbsolutePath();
    }

    // ? 2 MANERA DE GUARDAR UNA IMAGEN EN LOCAL
    default void saveQrLocal(byte[] file, String filename, String pathQr) {
        Path rutaArchivo = setPathImage(filename, pathQr);
        try (OutputStream outputStream = Files.newOutputStream(rutaArchivo)) {
            outputStream.write(file);
        } catch (IOException e) {

            throw new GenericException("Hubo un problema al guardar el Qr en local", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
