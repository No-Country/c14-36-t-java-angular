package com.nocountry.cashier.exception.handler;

import com.nocountry.cashier.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ROMULO
 * @package com.nocountry.cashier.exception.handler
 * @license Lrpa, zephyr cygnus
 * @since 10/10/2023
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Sirve para mostrar los mensajes de spring validator
     *
     * @param exception
     * @param headers
     * @param status
     * @param webRequest
     * @return
     */
    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {

        List<ApiError> errors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> ApiError.builder()
                        .field(e.getField())
                        .message(e.getDefaultMessage())
                        .build())
                .toList();
        ErrorResponseMessage error = ErrorResponseMessage.builder()
                .errors(errors)
                .message(exception.getTitleMessageCode())
                .path(webRequest.getDescription(false).replace("uri=", ""))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DuplicateEntityException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleDuplicateEntityException(DuplicateEntityException ex, HttpServletRequest request) {
        ProblemDetail problemDetails = ProblemDetail
                .forStatusAndDetail
                        (HttpStatus.BAD_REQUEST, ex.getMostSpecificCause().getMessage());
        problemDetails.setProperty("date", LocalDateTime.now());
        problemDetails.setInstance(URI.create(request.getRequestURL().toString()));
        return problemDetails;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = InvalidEmailException.class)
    public ProblemDetail handleNotFoundException(RuntimeException ex) {
        return ProblemDetail
                .forStatusAndDetail
                        (HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler(value = {GenericException.class})
    public ProblemDetail genericExceptionHandler(GenericException genericException, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                genericException.getHttpStatus(), genericException.getLocalizedMessage()
        );
        problemDetail.setInstance(URI.create(request.getRequestURL().toString())); //getDescription(false).replace("uri=","")
        problemDetail.setTitle(genericException.getHttpStatus().getReasonPhrase());
        problemDetail.setProperty("date", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(value = {JwtGenericException.class})
    public ProblemDetail genericJWTException(JwtGenericException jwt, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                jwt.getHttpStatus(), jwt.getLocalizedMessage()
        );
        problemDetail.setInstance(URI.create(request.getRequestURL().toString())); //getDescription(false).replace("uri=","")
        problemDetail.setTitle(jwt.getHttpStatus().getReasonPhrase());
        problemDetail.setProperty("date", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseMessage> handleConstraintViolationException(ConstraintViolationException constraintViolationException, WebRequest webRequest) {
        List<ApiError> details = new ArrayList<>();
        for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
            ApiError build = ApiError.builder()
                    .field(String.valueOf(violation.getPropertyPath()))
                    .message(violation.getMessage())
                    .build();
            details.add(build);
        }
        var apiConstraintViolationException = ErrorResponseMessage.builder()
                .errors(details)
                .message(constraintViolationException.getMessage())
                .path(webRequest.getDescription(false).replace("uri=", ""))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiConstraintViolationException);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handlePSQLException(DataIntegrityViolationException ex) {
        String message = ex.getMostSpecificCause().getMessage();
        if (message.contains("Detail:"))
            message = message.substring(message.lastIndexOf("Detail")).replace("Detail:", "");
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, message
        );
        problemDetail.setProperty("timestamp", LocalDateTime.now());
        return problemDetail;
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleResouceNotFoundE(ResourceNotFoundException ex, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getLocalizedMessage()
        );
        problemDetail.setInstance(URI.create(request.getRequestURL().toString())); //getDescription(false).replace("uri=","")
        problemDetail.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
        problemDetail.setProperty("date", LocalDateTime.now());
        return problemDetail;
    }

    @ExceptionHandler(InputNotValidException.class)
    public ProblemDetail handleResouceNotFoundE(InputNotValidException ex, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                ex.getStatusCode(), ex.getLocalizedMessage()
        );
        problemDetail.setInstance(URI.create(request.getRequestURL().toString()));
        problemDetail.setTitle(ex.getStatusCode().toString());
        problemDetail.setProperty("date", LocalDateTime.now());
        return problemDetail;
    }


}