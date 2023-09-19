package com.example.springbootminiproject.controller;

import com.example.springbootminiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {

    private UserService userService;

    // Creates a UserService instance
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/hello/")   //http://localhost:9094/auth/users/hello/
    public String hello() {
        return "Hello";
    }
}
