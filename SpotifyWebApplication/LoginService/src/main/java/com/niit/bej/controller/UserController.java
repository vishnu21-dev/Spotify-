package com.niit.bej.controller;

import com.niit.bej.domain.User;
import com.niit.bej.exception.UserAlreadyExists;
import com.niit.bej.exception.UserNotFound;
import com.niit.bej.security.SecurityTokenGenerator;
import com.niit.bej.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController

@RequestMapping("/userAuth")
public class UserController {
    private final UserService userService;
    private final SecurityTokenGenerator securityTokenGenerator;

    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registerUser = userService.resgisterUser(user);
            return new ResponseEntity<>(registerUser, HttpStatus.OK);
        } catch (UserAlreadyExists e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/userLogin")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User loginUser = userService.loginUser(user);
            Map<String, String> tokenGenerated;
            tokenGenerated = securityTokenGenerator.generateToken(loginUser);
            return new ResponseEntity<>(tokenGenerated, HttpStatus.OK);
        } catch (UserNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
