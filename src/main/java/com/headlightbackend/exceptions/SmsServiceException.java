package com.headlightbackend.exceptions;

public class SmsServiceException extends RuntimeException {
    public SmsServiceException(String message) {
        super(message);
    }
}
