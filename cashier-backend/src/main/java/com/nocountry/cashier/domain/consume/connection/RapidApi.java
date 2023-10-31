package com.nocountry.cashier.domain.consume.connection;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.consume.connection
 * @license Lrpa, zephyr cygnus
 * @since 28/10/2023
 */
@Component
@NoArgsConstructor
@Slf4j
public class RapidApi {

    @Value("${sms.api.key}")
    private String apiKey;
    @Value("${sms.api.host}")
    private String host;
    @Value("${sms.api.url}")
    private String url;
    @Value("${sms.rapidapi.key}")
    private String rapidApi;

    public String setApiConnection(String phoneNumber,String codeOtp){

        HttpClient client= HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", rapidApi)
                .header("X-RapidAPI-Host", host)
                .method("POST", HttpRequest.BodyPublishers.ofString("to=%2B"+phoneNumber+"&p="+apiKey+"&text=Verification%20code%20"+codeOtp))
                .build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .join();
    }
}
