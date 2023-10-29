package com.nocountry.cashier.domain.consume.service;

import com.nocountry.cashier.controller.dto.request.CurrencyDTO;
import com.nocountry.cashier.controller.dto.response.CurrencyResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.consume.service
 * @license Lrpa, zephyr cygnus
 * @since 27/10/2023
 */
public interface CurrencyExchangeService {

    List<CurrencyDTO> getCurrenciesSymbols();

    CurrencyResponseDTO convertCurrency(String to, String from, Double amount);

    default <K, V> List<V> getListOfValues(Map<K, V> map) {
        return new ArrayList<>(map.values());
    }

    default <K, V> List<K> getListOfKeys(Map<K, V> map) {
        return new ArrayList<>(map.keySet());
    }
}
