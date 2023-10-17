package com.nocountry.cashier.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.controller.dto.response
 * @license Lrpa, zephyr cygnus
 * @since 12/10/2023
 */

@Getter
public final class GenericResponseDTO<T> implements Serializable {

    @Serial
    private  static final long serialVersionUID = 1L;
    private final boolean success;
    private final String message;
    private final T data;
    private final LocalDateTime timeStamp;

    public GenericResponseDTO(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timeStamp= LocalDateTime.now();
    }
}
