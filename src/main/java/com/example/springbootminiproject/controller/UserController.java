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
        // add check for current logged in user?
        return "Hello";
    }

    /**
     * Endpoint to create a new User in the database
     * @param userObject The User passed in by the Http Request
     * @return The User after being saved in the database
     */
    @PostMapping(path = "/register/")   //http://localhost:9094/auth/users/register/
    public User createUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }

    /**
     * Endpoint to log in a User
     * @param loginRequest The Http request, contains an email address and password to be matched to entry in database
     * @return 200 Ok if log in is successful, 401 Unauthorized if not.
     */
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
