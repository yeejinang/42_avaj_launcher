package com.avaj_launcher.exception;

// a custom class
public class OutOfRangeException extends RuntimeException {

    public OutOfRangeException(String errorMessage) {  
        super(errorMessage);  
    }  
}