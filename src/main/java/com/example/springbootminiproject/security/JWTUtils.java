package com.example.springbootminiproject.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class JWTUtils {

    // Creates a logger for this class
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    // Key used to hash authentication token (JWT)
    @Value("C6UlILsE6GJwNqwCTkkvJj9O653yJUoteWMLfYyrc3vaGrrTOrJFAUD1wEBnnposzcQl")
    private String jwtSecret;

    // JWT Token expires after 86400000 milliseconds
    @Value("${jwt-expiration-ms}")
    private int jwtExpMS;

    /**
     * Generates a JWT token based on the given user after successful login
     * @param myUserDetails represents the logged in User
     * @return A JWT token generated from the logged in User's email address.
     */
    public String generateJwtToken(MyUserDetails myUserDetails) {
        return Jwts.builder()
                .setSubject(myUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpMS))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}
