package br.com.application.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest req) {
        FieldError fieldError = getFieldsErrors(ex);
        String field = String.format("Validation error in field: '%s'", fieldError.getField());
        String message = fieldError.getDefaultMessage();

        StandardError error = new StandardError();
        error.setError(field);
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(message);
        error.setPath(req.getRequestURI());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    private FieldError getFieldsErrors(MethodArgumentNotValidException ex){
        return (FieldError) ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .orElse(null);
    }
}
