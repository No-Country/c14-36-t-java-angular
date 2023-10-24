package com.nocountry.cashier.exception.handlefirebase;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;

/**
 * @author ROMULO
 * @package com.lrpa.app.exception.handlefirebase
 * @license Lrpa, zephyr cygnus
 * @since 12/9/2023
 */

@RestControllerAdvice
public class HandleExceptionFirebase {

    @ExceptionHandler(value = {ServiceAccountFirebase.class})
    public ProblemDetail handleServiceAccountException(ServiceAccountFirebase exception, HttpServletRequest request) {
        Class<?> exceptionClass = FileSizeLimitExceededException.class;
        HttpStatus httpStatus = null;
        if (exceptionClass.isAnnotationPresent(ResponseStatus.class)) {
            ResponseStatus responseStatus = exceptionClass.getAnnotation(ResponseStatus.class);
            httpStatus = responseStatus.value();
        }
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
              httpStatus, exception.getLocalizedMessage()
        );
        problemDetail.setType(URI.create(request.getRequestURL().toString())); //getDescription(false).replace("uri=","")
        problemDetail.setProperty("date", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(value = {FileSizeLimitExceededException.class})
    public ProblemDetail handleFileSizeException(FileSizeLimitExceededException exception, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.PAYLOAD_TOO_LARGE, exception.getLocalizedMessage()
        );
        problemDetail.setType(URI.create(request.getRequestURL().toString())); //getDescription(false).replace("uri=","")
        problemDetail.setProperty("date", LocalDateTime.now());
        return problemDetail;
    }
    @ExceptionHandler(value = {InvalidFileFormatException.class})
    public ProblemDetail handleFileSizeException(InvalidFileFormatException exception, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE, exception.getMessage()
        );
        problemDetail.setType(URI.create(request.getRequestURL().toString())); //getDescription(false).replace("uri=","")
        problemDetail.setProperty("date", LocalDateTime.now());
        return problemDetail;
    }
}
