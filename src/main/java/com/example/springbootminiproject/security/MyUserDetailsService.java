package com.example.springbootminiproject.security;

import com.example.springbootminiproject.model.User;
import com.example.springbootminiproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Finds the UserDetails of the user with the given email address.
     * @param emailAddress The email address passed in by the Http request.
     * @return A new MyUserDetails object that contains the details of the User with the given email address.
     * @throws UsernameNotFoundException Error if a User with the given email address is not found"
     */
    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userService.findUserByEmailAddress(emailAddress);
        return new MyUserDetails(user);
    }
}
