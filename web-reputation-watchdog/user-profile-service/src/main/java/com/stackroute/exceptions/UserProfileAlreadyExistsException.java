package com.stackroute.exceptions;

/**
 * Custom Exception to throw if Userprofile already exists
 */
public class UserProfileAlreadyExistsException extends Exception{
    private String message;

    public UserProfileAlreadyExistsException() {
    }

    public UserProfileAlreadyExistsException(String message) {
        this.message = message;
    }
}
