package com.niit.bej.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "user not present in database")

public class UserNotFound extends Throwable {
    public UserNotFound(String message) {
        super(message);
    }
}
