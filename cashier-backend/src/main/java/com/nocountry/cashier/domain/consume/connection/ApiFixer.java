package com.nocountry.cashier.domain.consume.connection;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.consume
 * @license Lrpa, zephyr cygnus
 * @since 27/10/2023
 */
@Component
@NoArgsConstructor
@Slf4j
public class ApiFixer {

    @Value("${fixer.api.key}")
    private String apiKey;
    @Value("${fixer.api.url}")
    private String urlFixer;


    public String setApiConnection(String path) {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(urlFixer.concat(path)))
                    .header("apiKey", apiKey)
                    .build();
        } catch (URISyntaxException e) {
            log.error("Error in URI api fixer");
        }
        String response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
        if (response.contains("API rate limit")) {
            log.error("API rate limit");
            return null;
        }
        return response;
    }


}
