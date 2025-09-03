package com.gaur.Assignment.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleDataNotFoundException(DataNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<String> handleDataAlreadyExistException(DataAlreadyExistsException ex) {
        String msg = "Data already exist: " + ex.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(msg);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String message = "Invalid Id format. Please provide a valid numeric User ID";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
