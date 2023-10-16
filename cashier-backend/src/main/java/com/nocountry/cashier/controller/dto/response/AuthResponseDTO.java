package com.nocountry.cashier.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
    private final String token;
    private final String message;
    private final LocalDateTime timeStamp;
    private final String id;

    @Builder
    public AuthResponseDTO(String token,String message,String id) {
        this.token = token;
        this.message=message;
        this.id= id;
        this.timeStamp = LocalDateTime.now();
    }
}
