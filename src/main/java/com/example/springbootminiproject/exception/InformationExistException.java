package com.example.springbootminiproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InformationExistException extends RuntimeException {

    //This exception gets thrown when we are trying to create or update an entry in the database, and the database already has an entry
    //that matches what the Http request is passing in.

    public InformationExistException(String message) {
        super(message);
    }
}
