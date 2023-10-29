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
 * @since 20/10/2023
 */
@Getter
public class AuthConfirmedDTO  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;
    private final String id;
    private final LocalDateTime timeStamp;
    private final String qr;

    @Builder
    public AuthConfirmedDTO(String id, String message, String qr) {
        this.id = id;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
        this.qr = qr;
    }
}
