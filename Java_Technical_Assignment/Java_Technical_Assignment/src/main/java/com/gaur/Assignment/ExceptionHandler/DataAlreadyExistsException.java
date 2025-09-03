package com.gaur.Assignment.ExceptionHandler;

public class DataAlreadyExistsException extends RuntimeException{
    public DataAlreadyExistsException(String message){
        super(message);
    }
}
