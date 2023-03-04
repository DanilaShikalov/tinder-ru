package com.example.digitalproject.exceptions;

import com.example.digitalproject.models.dto.error.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.CONFLICT;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> generateNotFoundException(ResponseStatusException ex) {
        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(ex.getBody().getStatus())
                .status(ex.getStatusCode())
                .description(ex.getReason())
                .date(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorDTO, CONFLICT);
    }
}
