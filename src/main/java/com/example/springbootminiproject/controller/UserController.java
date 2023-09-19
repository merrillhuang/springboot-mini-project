package com.example.springbootminiproject.controller;

import com.example.springbootminiproject.model.User;
import com.example.springbootminiproject.model.request.LoginRequest;
import com.example.springbootminiproject.model.response.LoginResponse;
import com.example.springbootminiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PostMapping(path = "/register/")   //http://localhost:9094/auth/users/register/
    public User createUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }

    @PostMapping(path = "/login")   //http://localhost:9094/auth/users/login/
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<String> jwtToken = userService.loginUser(loginRequest);
        if (jwtToken.isPresent()) {
            return ResponseEntity.ok(new LoginResponse(jwtToken.get()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body((new LoginResponse("Authentication failed")));
        }
    }
}
