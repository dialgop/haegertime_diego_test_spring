package com.example.Haegertime_diego_spring.exceptions;

public class UserAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 1904585489531578456L;

    public UserAlreadyExistsException(String message) {
        super(message);
    }



}
