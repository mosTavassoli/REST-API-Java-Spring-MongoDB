package com.illimity.mostafa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerPasswordException extends RuntimeException {
    public CustomerPasswordException(String message) {
        super(message);
    }
}
