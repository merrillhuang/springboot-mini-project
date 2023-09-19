package com.example.springbootminiproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity  //enables need to authorize every method before executing?
public class SecurityConfiguration {

    // Produces passwordEncoder objects
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Filters incoming requests to match certain parameters
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/auth/users", "/auth/users/login/", "/auth/users/register/").permitAll()  //automatically authorizes all http requests to listed endpoints
                .antMatchers("/h2-console/**").permitAll()  //automatically authorizes all http requests to endpoint that begin with /h2-console
                .anyRequest().authenticated()
                .and().sessionManagement()
                .and().csrf().disable()
                .headers().frameOptions().disable();
        return http.build();
    }

    // Creates AuthenticationManager objects
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
