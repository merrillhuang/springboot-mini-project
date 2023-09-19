package com.example.springbootminiproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    // Creates logger for this class
    Logger logger = Logger.getLogger(JwtRequestFilter.class.getName());

    private MyUserDetailsService myUserDetailsService;

    private JWTUtils jwtUtils;

    // Creates a MyUserDetailsService instance
    @Autowired
    public void setMyUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    // Creates a JWTUtils instance
    @Autowired
    public void setJwtUtils(JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /**
     * Extracts the token from the Http request
     * @param request Represents the content of the Http request
     * @return The JWT token if it exists, null if it does not
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (StringUtils.hasLength(headerAuth) && headerAuth.startsWith("Bearer")) {
            return headerAuth.substring(7);
        } else {
            logger.info("No header");
            return null;
        }
    }

    /**
     * Loads the current logged in user's details into the application's memory?
     * @param request The request from the client.
     * @param response The response after processing the request (e.g. 200 OK, 404 not found etc)?
     * @param filterChain The filter that decides whether the user has access to the request endpoint?
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = this.myUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            logger.info("Cannot set user authentication token");
        }
        filterChain.doFilter(request, response);
    }
}
