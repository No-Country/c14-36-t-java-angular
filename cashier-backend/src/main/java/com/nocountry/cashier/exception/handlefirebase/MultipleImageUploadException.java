package com.nocountry.cashier.exception.handlefirebase;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ROMULO
 * @package com.lrpa.app.exception.handlefirebase
 * @license Lrpa, zephyr cygnus
 * @since 12/9/2023
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MultipleImageUploadException extends RuntimeException{
    public MultipleImageUploadException(String  error){
        super(error);
    }
}
