package com.nocountry.cashier.exception.handlefirebase;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ROMULO
 * @package com.lrpa.app.exception
 * @license Lrpa, zephyr cygnus
 * @since 11/9/2023
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class ServiceAccountFirebase extends RuntimeException{
    public ServiceAccountFirebase(String  error){
        super(error);
    }
}
