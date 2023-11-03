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
 * @since 20/10/2023
 */
@Getter
@JsonPropertyOrder({"id","message", "token", "timeStamp"})
public class AuthenticatedUserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String id;
    private final String token;
    private final String message;
    private final boolean success;
    private final LocalDateTime timeStamp;

    @Builder
    public AuthenticatedUserDTO(String token,String message,String id,boolean success) {
        this.id=id;
        this.success=success;
        this.token = token;
        this.message=message;
        this.timeStamp = LocalDateTime.now();
    }
}
