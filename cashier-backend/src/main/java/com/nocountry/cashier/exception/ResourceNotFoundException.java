package com.nocountry.cashier.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.exception
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public final class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s ;fieldName: %s - fieldValue: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String message,String resourceName) {
        super(String.format("%s %s",message,resourceName));
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(Object fieldValue) {
        super(String.valueOf(fieldValue));
        this.fieldValue = fieldValue;
    }
}
