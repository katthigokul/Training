package com.stackroute.exceptions;

/**
 * Custom Exception to throw if entity is not found
 */
public class EntityNotFoundException extends Exception {

    private String message;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {

        this.message = message;
    }
}
