package com.headlightbackend.exceptions;

public class EmptyOrdersListException extends RuntimeException {
    public EmptyOrdersListException(String message) {
        super(message);
    }
}
