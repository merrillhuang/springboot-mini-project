package com.example.springbootminiproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {

    @GetMapping(path = "/hello/")
    public String hello() {
        return "Hello";
    }
}
