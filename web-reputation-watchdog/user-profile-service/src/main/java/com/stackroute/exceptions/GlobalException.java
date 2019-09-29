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
     * Handles UserProfileNotFound exception
     */
    @ExceptionHandler(value = UserProfileNotFoundException.class)
    public ResponseEntity<Object> notFoundException(UserProfileNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles UserProfileAlreadyExistsException
     */
    @ExceptionHandler(value = UserProfileAlreadyExistsException.class)
    public ResponseEntity<Object> alreadyExistsException(UserProfileAlreadyExistsException exception) {
        return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
    }

    /**
     * Handles Internal_Server_Error i.e if database connection fails
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> databaseConnectionFailsException(Exception exception) {
        return new ResponseEntity<>("Database connectivity is lost", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

