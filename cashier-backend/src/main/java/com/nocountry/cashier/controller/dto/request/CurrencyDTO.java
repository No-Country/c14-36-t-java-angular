package com.nocountry.cashier.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.domain.consume.dto
 * @license Lrpa, zephyr cygnus
 * @since 27/10/2023
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyDTO {
    private String codeCurrency;
    private String valueCurrency;
}
