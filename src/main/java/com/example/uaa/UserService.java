package com.example.uaa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserData userData;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userData.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
