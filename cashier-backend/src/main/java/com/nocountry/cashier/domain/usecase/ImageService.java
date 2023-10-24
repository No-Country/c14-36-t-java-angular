package com.nocountry.cashier.domain.usecase;


import com.nocountry.cashier.controller.dto.response.ImageResponseDTO;
import com.nocountry.cashier.exception.handlefirebase.FileSizeLimitExceededException;
import com.nocountry.cashier.exception.handlefirebase.InvalidFileFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.usecase
 * @license Lrpa, zephyr cygnus
 * @since 11/10/2023
 */
public interface ImageService {
    ResponseEntity<?> generateImage(String fileImage);
    ImageResponseDTO uploadImages(MultipartFile files);
    String saveImageToDb(MultipartFile archivo);

    default String validateFileSizeAndFormat(MultipartFile archivo) {
        long fileSize = archivo.getSize();
        long maxSize = (long) (8 * Math.pow(1_024,2));
        String fileType = Objects.requireNonNull(archivo.getOriginalFilename()).toLowerCase();
        if (fileSize > maxSize) throw new FileSizeLimitExceededException("El archivo no debe ser grande máximo 8mb");
        if (!fileType.endsWith(".jpeg")
                && !fileType.endsWith(".png")
                && !fileType.endsWith(".jpg")) throw new InvalidFileFormatException("El archivo debe ser tipo image/jpg o image/png");
        return fileType;
    }
}
