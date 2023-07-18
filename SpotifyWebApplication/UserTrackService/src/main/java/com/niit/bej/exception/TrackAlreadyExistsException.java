package com.niit.bej.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Track Already Exists!")
public class TrackAlreadyExistsException extends Exception {
    public TrackAlreadyExistsException(String message) {
        super(message);
    }
}
