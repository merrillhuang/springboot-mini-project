package com.example.springbootminiproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {

    //This exception is thrown when an entry matching the details passed in by the Http request is not found.

    public InformationNotFoundException(String message) {
        super(message);
    }
}
