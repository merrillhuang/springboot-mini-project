package com.example.springbootminiproject.service;

import com.example.springbootminiproject.model.User;
import com.example.springbootminiproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Uses SQL statement to find a User from the database by their email address.
     * @param emailAddress The email address passed in by the HttpRequest
     * @return The User that has the corresponding email address.
     */
    public User findUserByEmailAddress(String emailAddress) {
        return userRepository.findUserByEmailAddress(emailAddress);
    }
}
