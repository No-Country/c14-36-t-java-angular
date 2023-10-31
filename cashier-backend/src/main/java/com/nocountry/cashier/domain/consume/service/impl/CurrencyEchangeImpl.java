package com.nocountry.cashier.domain.consume.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nocountry.cashier.controller.dto.request.CurrencyDTO;
import com.nocountry.cashier.controller.dto.response.CurrencyResponseDTO;
import com.nocountry.cashier.domain.consume.connection.ApiFixer;
import com.nocountry.cashier.domain.consume.service.CurrencyExchangeService;
import com.nocountry.cashier.exception.GenericException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.consume.service.impl
 * @license Lrpa, zephyr cygnus
 * @since 27/10/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyEchangeImpl implements CurrencyExchangeService {

    private final ApiFixer apiFixer;
    @Value("${fixer.api.url.convert}")
    private String urlConvert;

    @Override
    public List<CurrencyDTO> getCurrenciesSymbols() {
        final String symbols="latest?symbols=&base=USD";
        ObjectMapper objectMapper = new ObjectMapper();
        String json = apiFixer.setApiConnection(symbols);

        try {
            //aqui esta mal
            String rates = objectMapper.readValue(json, JsonNode.class).get("rates").asText();
            Map<String, Double> map = objectMapper.readValue(rates, new TypeReference<>() {
            });

            //ESTOY INTENTANDO TRAER LOS RATES {PEN:3.8}
            List<String> listOfKeys = getListOfKeys(map);
            List<Double> listOfValues = getListOfValues(map);

            System.out.println(listOfKeys.toString());
            System.out.println(listOfValues.toString());

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    /**
     * 100 USD to PEN = 386.9345
     * @param to String currency code
     * @param from String currency code
     * @param amount Double amount to convert
     * @return CurrencyResponseDTO
     */
    @Override
    public CurrencyResponseDTO convertCurrency(String to, String from, Double amount) {
        ObjectMapper objectMapper = new ObjectMapper();
        String format = String.format(urlConvert, to, from, amount);
        String json = apiFixer.setApiConnection(format);
        try {
            JsonNode jsonNode = objectMapper.readValue(json, JsonNode.class);
            double result = jsonNode.get("result").asDouble();
            double rate = jsonNode.get("info").get("rate").asDouble();
            return CurrencyResponseDTO.builder()
                    .from(from)
                    .to(to)
                    .rate(BigDecimal.valueOf(rate).setScale(2, RoundingMode.HALF_UP))
                    .result(BigDecimal.valueOf(result).setScale(2, RoundingMode.HALF_UP))
                    .build();
        } catch (JsonProcessingException e) {
            log.error("Error in convert currency in method convertCurrency");
            throw new GenericException("Error in convert currency", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
