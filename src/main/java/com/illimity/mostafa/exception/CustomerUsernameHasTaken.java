package com.illimity.mostafa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerUsernameHasTaken extends RuntimeException {
    public CustomerUsernameHasTaken(String message) {
        super(message);
    }
}
