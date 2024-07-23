package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundeException extends RuntimeException{

    public ResourceNotFoundeException(String message) {
        super(message);
    }
}
