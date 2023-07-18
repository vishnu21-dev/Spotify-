package com.niit.bej.service;

import com.niit.bej.domain.User;
import com.niit.bej.exception.UserAlreadyExists;
import com.niit.bej.exception.UserNotFound;
import com.niit.bej.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @Override
    public User resgisterUser(User user) throws UserAlreadyExists {
        if(userRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExists("User Already present ");
        }
        else {
            return userRepository.save(user);
        }
    }

    @Override
    public User loginUser(User user) throws UserNotFound {
        if (userRepository.findById(user.getEmailId()).isPresent()) {
            User loggedInUser = userRepository.findById(user.getEmailId()).get();
            if (loggedInUser.getPassword().equals(user.getPassword())) return loggedInUser;
            else throw new UserNotFound("Password given is not matching");
        } else throw new UserNotFound("User not present in database Please register");
    }
    }

