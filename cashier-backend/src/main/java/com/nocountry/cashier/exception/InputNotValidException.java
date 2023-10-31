package com.nocountry.cashier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.exception
 * @license Lrpa, zephyr cygnus
 * @since 27/10/2023
 */
public class InputNotValidException extends ResponseStatusException {
    public InputNotValidException(String message) {
        super(HttpStatus.BAD_REQUEST,message);
    }
}
