package com.niit.bej.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Track Already Exists!")
public class PlayListAlreadyExistException extends Exception {
    public PlayListAlreadyExistException(String message) {
        super(message);
    }
}
