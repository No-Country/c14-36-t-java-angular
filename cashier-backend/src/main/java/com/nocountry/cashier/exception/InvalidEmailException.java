package com.nocountry.cashier.exception;

import java.io.Serial;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.exception
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
public class InvalidEmailException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public InvalidEmailException(String message) {
        super(message);
    }
}
