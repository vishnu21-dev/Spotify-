package com.niit.bej.service;

import com.niit.bej.domain.User;
import com.niit.bej.exception.UserAlreadyExists;
import com.niit.bej.exception.UserNotFound;

public interface UserService {
    User resgisterUser(User user) throws UserAlreadyExists;

    User loginUser(User user) throws UserNotFound;
}
