package com.gaur.Assignment.ExceptionHandler;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String message){
        super(message);
    }
}
