package com.mobiliz.config.security;
import com.mobiliz.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class JwtUserDetails implements UserDetailsService {
    @Autowired
    private VehicleService vehicleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    public UserDetails loadUserByRole(String role) throws UsernameNotFoundException {

            return User.builder()
                    .username(role)
                    .password("")
                    .accountLocked(false)
                    .accountExpired(false)
                    .authorities(new SimpleGrantedAuthority(role))
                    .build();

    }
}

