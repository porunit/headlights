package com.headlightbackend.controllers;

import com.headlightbackend.data.dto.ErrorDTO;
import com.headlightbackend.exceptions.EmptyOrdersListException;
import com.headlightbackend.exceptions.UsernameTakenException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e) {
        logError(e);
        //Arrays.stream(e.getStackTrace()).forEach(log::error);
        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException e) {
        logError(e);
        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameTakenException.class)
    public ResponseEntity<ErrorDTO> handleUsernameTakenException(UsernameTakenException e){
        logError(e);
        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmptyOrdersListException.class)
    public ResponseEntity<ErrorDTO> handleEmptyOrdersListException(EmptyOrdersListException e){
        logError(e);
        return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
    }
    private void logError(Exception e) {
        log.error(e.getMessage());
        log.error(e.getClass());
    }
}
