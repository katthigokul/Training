package com.stackroute.exceptions;

/**
 * Custom Exception to throw if entity already exists
 */
public class EntityAlreadyExistsException extends Exception {

    private String message;

    public EntityAlreadyExistsException() {
    }

    public EntityAlreadyExistsException(String message) {
        this.message = message;
    }
}
