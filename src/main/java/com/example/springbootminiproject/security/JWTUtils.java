package com.example.springbootminiproject.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class JWTUtils {

    // Creates a logger for this class
    Logger logger = Logger.getLogger(JWTUtils.class.getName());

    // Key used to hash authentication token (JWT)
    @Value("${jwt-secret}")
    private String jwtSecret;

    // JWT Token expires after 86400000 milliseconds
    @Value("${jwt-expiration-ms}")
    private int jwtExpMS;

    /**
     * Generates a JWT token based on the given user after successful login.
     * @param myUserDetails Represents the logged in User.
     * @return A JWT token generated from the logged in User's email address.
     * Runs once on successful login
     */
    public String generateJwtToken(MyUserDetails myUserDetails) {
        return Jwts.builder()
                .setSubject(myUserDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpMS))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    /**
     * Decodes token passed in from Http request to retrieve the username of the current logged in User.
     * @param token Token passed in by the Http request.
     * @return The username of the current logged in user decoded from the token.
     * Runs for every request sent after log in. Validates that the correct user is still logged in.
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validates that the token passed in from the Http request is correct and is not an invalid token.
     * @param token Token passed in by the Http request
     * @return True if token is valid and matches, false if not.
     */
    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SecurityException e) {
            logger.log(Level.SEVERE, "Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.log(Level.SEVERE, "Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.log(Level.SEVERE, "JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.log(Level.SEVERE, "JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
