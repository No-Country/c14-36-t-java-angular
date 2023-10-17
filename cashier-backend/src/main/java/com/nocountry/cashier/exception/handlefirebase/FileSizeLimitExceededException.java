package com.nocountry.cashier.exception.handlefirebase;

/**
 * @author ROMULO
 * @package com.lrpa.app.exception.handlefirebase
 * @license Lrpa, zephyr cygnus
 * @since 12/9/2023
 */
public class FileSizeLimitExceededException extends RuntimeException{
    public FileSizeLimitExceededException(String message) {
        super(message);
    }
}
