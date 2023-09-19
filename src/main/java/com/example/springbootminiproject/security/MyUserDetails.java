package com.example.springbootminiproject.security;

import com.example.springbootminiproject.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class MyUserDetails implements UserDetails {

    private final User user;

    public MyUserDetails(User user) {
        this.user = user;
    }

    // The roles that this User has.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmailAddress();      //we use the User's email as the username when validating
    }

    // Is the account non expired.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Is the account not locked.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Are the account's credentials expired.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Is the account enabled.
    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}
