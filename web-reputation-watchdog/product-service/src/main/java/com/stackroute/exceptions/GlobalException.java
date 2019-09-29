package com.stackroute.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @ControllerAdvice annotation provided by Spring allows you to write global
 * code that can be applied to a wide range of controllers
 */
@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    /**
     * Handles TrackNotFound exception
     */
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> notFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>("Entity you want to get is not found", HttpStatus.NOT_FOUND);
    }

    /**
     * Handles TrackAlreadyExistsException
     */
    @ExceptionHandler(value = EntityAlreadyExistsException.class)
    public ResponseEntity<Object> alreadyExistsException(EntityAlreadyExistsException exception) {
        return new ResponseEntity<>("Entity already exists", HttpStatus.CONFLICT);
    }

    /**
     * Handles Internal_Server_Error i.e if database connection fails
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
        return new ResponseEntity<>("Database connectivity is lost", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

