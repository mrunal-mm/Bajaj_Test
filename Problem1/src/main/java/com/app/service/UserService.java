package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.app.entity.User;

import javax.validation.Valid;

@Service
@Validated
public class UserService {

    @Autowired
    private com.app.repository.UserRepository userRepository;

    public User createUser(@Valid User user) {
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already in use");
        }
        if (userRepository.existsByEmailId(user.getEmailId())) {
            throw new IllegalArgumentException("Email ID already in use");
        }
        return userRepository.save(user);
    }
}
