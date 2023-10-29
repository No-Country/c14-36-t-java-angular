package com.nocountry.cashier.domain.service.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.common.base.Strings;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.nocountry.cashier.configuration.FirebaseProperties;
import com.nocountry.cashier.controller.dto.response.ImageResponseDTO;
import com.nocountry.cashier.domain.usecase.firebase.FirebaseService;
import com.nocountry.cashier.exception.GenericException;
import com.nocountry.cashier.exception.handlefirebase.MultipleImageUploadException;
import com.nocountry.cashier.exception.handlefirebase.ServiceAccountFirebase;
import com.nocountry.cashier.persistance.entity.ImageEntity;
import com.nocountry.cashier.persistance.mapper.ImageMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.service
 * @license Lrpa, zephyr cygnus
 * @since 11/10/2023
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FirebaseServiceImpl implements FirebaseService {

    // * RECURSO SACADO DE https://medium.com/@mahfuzcse12/generating-qr-in-spring-boot-application-a94005a5cb35
    // * https://medium.com/@danielangel22/how-to-generate-qr-codes-in-java-with-spring-boot-d22e6dbcc1c

    private final FirebaseProperties propertiesFirebase;
    private final ImageMapper imageMapper;
    @Value("${path.directory.not-found}")
    private String notFound;

    // ? INICIAMOS LAS CREDENCIALES , se ejecutará automáticamente después de que se haya completado la construcción del bean
    @SuppressWarnings("deprecation")
    @PostConstruct
    public void init() {
        try {
            FileInputStream serviceAccount = new FileInputStream(propertiesFirebase.getAccountKey());
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket(propertiesFirebase.getBucketName())
                    .build();
            FirebaseApp.initializeApp(options);
            log.info("SE CARGO CORRECTAMENTE LAS CREDENCIALES");
        } catch (Exception ex) {
            log.warn("Ocurrio algo en leer las llaves de autenticacion {}", ex.getMessage());
            throw new ServiceAccountFirebase("Error al autenticarse" + ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> generateImage(String fileImage) {
        Resource resource;
        String fileName;
        Path rutaImagen;

        String urlImageFirebase = this.getUrlImageFirebase(propertiesFirebase.getBucketName(), fileImage.strip());
        try {
            //? consulta si existe la url de firebase promedio de 0.8s
            if (!checkExistenceUrl(urlImageFirebase)) {
                rutaImagen = Paths.get(notFound).resolve("not_found.png").toAbsolutePath();
                resource = new UrlResource(rutaImagen.toUri());
                fileName = resource.getFilename();
                log.warn("Se cargo la imagen por defecto: " + fileName);
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_PNG);
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"");
                return new ResponseEntity<>(resource, headers, HttpStatus.NOT_FOUND);
            }
            URL url = new URL(urlImageFirebase);
            try (InputStream inputStream = url.openStream()) {
                byte[] imageBytes = inputStream.readAllBytes();
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG.toString()))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileImage + "\"")
                        .body(imageBytes);
            }
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HTTP Status will be NOT FOUND (CODE 404)\n");
        }
    }

    @Override
    public ImageResponseDTO uploadImages(MultipartFile photo) {
        if (photo.isEmpty()) throw new MultipleImageUploadException("Debe subir al menos una imagen");
        ImageEntity imagen;
        String file;

        file = this.saveImageToDb(photo);
        String[] urlFilename = file.split(" ");
        imagen = ImageEntity.builder()
                .fileName(urlFilename[1].strip())
                .fileType(photo.getContentType())
                .urlImage(urlFilename[0].strip())
                .build();

        log.info("imagen creada aun no guardada  -" + imagen.getUrlImage());

        return imageMapper.toImageDto(imagen);
    }

    @Override
    public String saveImageToDb(MultipartFile archivo) {
        log.info("DENTRO DE LA FUNCION SAVE IMAGE TO DB");
        // ? validamos internamente que cumpla el tamanio y el formato
        String fileType = validateFileSizeAndFormat(archivo);

        String name = generateFileName(fileType);
        String extension = archivo.getContentType();

        try {
            Storage storage = StorageClient.getInstance().bucket().getStorage();
            BlobId blobId = BlobId.of(propertiesFirebase.getBucketName(), name);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(extension).build();
            storage.create(blobInfo, archivo.getBytes());
            return this.getUrlImageFirebase(propertiesFirebase.getBucketName(), name);
        } catch (IOException e) {
            log.error("Ocurrio algo al guardar la imagen {}", name);
            StackTraceElement[] stackTrace = e.getStackTrace();
            if (stackTrace.length > 0) {
                String methodName = stackTrace[0].getMethodName();
                log.info("Método donde se lanzó la excepción: " + methodName);
            }
            throw new RuntimeException("No se pudo leer la imagen " + e.getCause().getMessage());
        }
    }

    @Override
    public String getUrlImageFirebase(String bucketName, String filename) {
        filename=filename.strip();
        String url = String.format(propertiesFirebase.getUrlImageFirebase(), bucketName, filename);
        return url+" "+filename;
    }

    @Override
    public void deleteImageFirebase(String fileName) {
        // *Storage storage = StorageOptions.getDefaultInstance().getService();
        // *Reemplaza con el nombre de tu depósito Firebase Storage
        log.info("Limpieza del nombre del archivo " + StringUtils.cleanPath(fileName));

        if (Strings.isNullOrEmpty(fileName)) throw new GenericException("No puede estar vacío", HttpStatus.BAD_REQUEST);

        Storage storage = StorageClient.getInstance().bucket().getStorage();
        Bucket bucket = storage.get(propertiesFirebase.getBucketName());
        Blob blob = bucket.get(fileName);
        log.info("dentro de la funcion delete firebase {}", fileName);
        if (blob == null) throw new GenericException("La imagen que intentas eliminar no existe", HttpStatus.NOT_FOUND);
        blob.delete();
        log.info("Imagen eliminada: " + fileName);
    }
}
