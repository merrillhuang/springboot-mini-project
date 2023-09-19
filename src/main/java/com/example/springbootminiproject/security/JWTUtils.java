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
}
