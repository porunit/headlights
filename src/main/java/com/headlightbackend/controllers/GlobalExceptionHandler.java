package com.headlightbackend.controllers;

import com.headlightbackend.data.dto.ErrorDTO;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e) {
        log.error(e.getMessage());
        Arrays.stream(e.getStackTrace()).forEach(log::error);
        return ResponseEntity.ok(new ErrorDTO(e.getMessage()));
    }
}
