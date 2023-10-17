package com.nocountry.cashier.domain.usecase;

import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.util.UUID;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.usecase
 * @license Lrpa, zephyr cygnus
 * @since 11/10/2023
 */
public interface FirebaseService extends ImageService{
    String getUrlImageFirebase(String bucketName, String filename);

    void deleteImageFirebase(String fileName);

    default String getExtension(String filename) {
        return StringUtils.getFilenameExtension(filename);
    }

    default String generateFileName(String originalFileName) {
        return UUID.randomUUID().toString().concat("." + getExtension(originalFileName));
    }

    default boolean checkExistenceUrl(String url) {
        int statusCode;

        try {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpHead httpHead = new HttpHead(url);
                try (CloseableHttpResponse httpResponse = httpClient.execute(httpHead)) {
                    statusCode = httpResponse.getStatusLine().getStatusCode();
                }
            }
            return statusCode == 200;
        } catch (IOException e) {
            return false;
        }
    }
}
