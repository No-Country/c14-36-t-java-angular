package com.nocountry.cashier.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.exception
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
public class DuplicateEntityException extends DataIntegrityViolationException {
    @Serial
    private static final long serialVersionUID = 1L;
    public DuplicateEntityException(String msg) {
        super(msg);
    }
}
