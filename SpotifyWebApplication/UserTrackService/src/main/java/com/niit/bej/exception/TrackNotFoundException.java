package com.niit.bej.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Track does not exist")
public class TrackNotFoundException extends Exception {
    public TrackNotFoundException(String message) {
        super(message);
    }
}
