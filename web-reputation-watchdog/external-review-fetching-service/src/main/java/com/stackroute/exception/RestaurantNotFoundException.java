package com.stackroute.exception;

import lombok.NoArgsConstructor;

/*
 *@NoArgsConstructor will generate constructor with no arguments
 * */
@NoArgsConstructor
/**
 * Custom Exception to throw if Restaurant not found
 */
public class RestaurantNotFoundException extends Exception {

    private String message;

    public RestaurantNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
