package com.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.app.entity.User;
import com.app.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/automation-campus")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create/user")
    public ResponseEntity<User> createUser(@RequestHeader("roll-number") String rollNumber,
                                            @Valid @RequestBody User user) {
        // Here you could validate the roll-number header if needed
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
