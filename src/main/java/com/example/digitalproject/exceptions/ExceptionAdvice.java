package com.example.digitalproject.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> generateNotFoundException(ResponseStatusException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }
}
