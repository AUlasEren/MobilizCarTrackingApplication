package com.mobiliz.config.security;


import com.mobiliz.repository.entity.UserAuthentication;
import com.mobiliz.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetails implements UserDetailsService {
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public UserDetails loadUserByAuthId(Long userAuthenticationId) throws UsernameNotFoundException {
        Optional<UserAuthentication> userAuthentication = userAuthenticationService.findById(userAuthenticationId);
        if(userAuthentication.isPresent()) {
            return User.builder()
                    .username(userAuthentication.get().getName())
                    .password("")
                    .accountLocked(false)
                    .accountExpired(false)
                    .authorities(new SimpleGrantedAuthority(userAuthentication.get().getRole().name()))
                    .build();
        }
        return null;
    }
}

