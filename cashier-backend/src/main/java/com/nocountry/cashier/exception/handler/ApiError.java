package com.nocountry.cashier.exception.handler;

import lombok.*;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.exception
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class ApiError {
    private String message;
    private String field;
}
