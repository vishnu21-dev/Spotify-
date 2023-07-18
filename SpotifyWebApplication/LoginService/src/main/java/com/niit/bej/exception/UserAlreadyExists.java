package com.niit.bej.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.CONFLICT,reason = "user already present in database")
public class UserAlreadyExists extends Exception{
    public UserAlreadyExists(String message){
        super(message);
    }
}
