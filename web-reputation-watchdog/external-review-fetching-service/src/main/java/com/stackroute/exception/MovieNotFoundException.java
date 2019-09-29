package com.stackroute.exception;

/**
 * Custom Exception to throw if Movie not found
 */
public class MovieNotFoundException extends Exception {

    private String message;

    public MovieNotFoundException() {
    }

    public MovieNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
