package com.niit.bej.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Play List Not Exists!")
public class PlayListNotPresentException extends Exception{
    public PlayListNotPresentException(String message) {
        super(message);
    }
}
