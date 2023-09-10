package com.headlightbackend.exceptions;

public class ObjectDoesntExistsException extends RuntimeException {
    public ObjectDoesntExistsException(String message) {
        super(message);
    }
}
