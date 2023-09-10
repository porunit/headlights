package com.headlightbackend.exceptions;

import java.io.File;

public class FileProcessingException extends RuntimeException{
    public FileProcessingException(String message){
        super(message);
    }
}
