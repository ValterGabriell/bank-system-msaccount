package io.github.valtergabriell.msaccount.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = {RequestExceptions.class})
    public ResponseEntity<Object> handleExceptionAPI(RequestExceptions e) {
        APIExceptions apiExceptions = new APIExceptions(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiExceptions, HttpStatus.BAD_REQUEST);
    }
}
