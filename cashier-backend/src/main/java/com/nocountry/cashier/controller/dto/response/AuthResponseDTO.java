package com.nocountry.cashier.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.controller.dto.response
 * @license Lrpa, zephyr cygnus
 * @since 13/10/2023
 */
@Getter
public class AuthResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;
    private final LocalDateTime timeStamp;


    @Builder
    public AuthResponseDTO(String message) {
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
