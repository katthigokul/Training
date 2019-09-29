package com.stackroute.exceptions;

/**
 * Custom Exception to throw if Userprofile is not found
 */
public class UserProfileNotFoundException extends Exception{
    private String message;

    public UserProfileNotFoundException() {
    }

    public UserProfileNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
