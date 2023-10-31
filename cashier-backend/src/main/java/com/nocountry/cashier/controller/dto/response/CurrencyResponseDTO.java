package com.nocountry.cashier.controller.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.consume.dto
 * @license Lrpa, zephyr cygnus
 * @since 27/10/2023
 */
@Getter
public class CurrencyResponseDTO {
    private final String to;
    private final String from;
    private final BigDecimal rate;
    private final BigDecimal result;
    private final LocalDateTime timeStamp;

    @Builder
    public CurrencyResponseDTO(String to, String from, BigDecimal rate, BigDecimal result) {
        this.to = to;
        this.from = from;
        this.rate = rate;
        this.result = result;
        this.timeStamp= LocalDateTime.now();
    }
}
