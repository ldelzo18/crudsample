package com.learning.crudsample.application.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {

    public UserDetails loadUserByUsername(String username);
}
