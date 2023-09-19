package com.example.springbootminiproject.model.request;

public class LoginRequest {

    // Represents the Http request sent by the User, contains an email address and password.

    private String emailAddress;

    private String password;

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }
}
