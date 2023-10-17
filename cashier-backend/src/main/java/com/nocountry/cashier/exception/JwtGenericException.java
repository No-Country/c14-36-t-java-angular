package com.nocountry.cashier.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.exception
 * @license Lrpa, zephyr cygnus
 * @since 14/10/2023
 */
@Getter
@Setter
public class JwtGenericException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    private final String message;
    private final HttpStatus httpStatus;
    private final LocalDateTime timeStamp;
    private final int status;

    public JwtGenericException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = LocalDateTime.now();
        this.status = httpStatus.value();
    }
}
