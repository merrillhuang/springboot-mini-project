package com.example.springbootminiproject.model.response;

public class LoginResponse {

    // Represents the Http response after processing a log in request. Contains a jwt generated from the logged in User.

    private String jwt;

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
