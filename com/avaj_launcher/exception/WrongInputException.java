package com.avaj_launcher.exception;

// a custom class
public class WrongInputException extends RuntimeException {

    public WrongInputException(String errorMessage) {  
        super(errorMessage);  
    }  
}