package com.mobiliz.config.security;


import com.mobiliz.exceptions.EErrorType;
import com.mobiliz.exceptions.VehicleManagerException;
import com.mobiliz.repository.enums.EStatus;
import com.mobiliz.utility.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

;

public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenManager jwtTokenManager;
    @Autowired
    JwtUserDetails jwtUserDetails;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader=request.getHeader("Authorization");
        if (authHeader!=null && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);
            Optional<String>userRole=jwtTokenManager.getRoleFromToken(token);
            System.out.println(userRole);
            if (userRole.isEmpty())
                throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
            UserDetails userDetails=jwtUserDetails.loadUserByRole(userRole.get());
            if (Objects.isNull(userDetails))
                throw new VehicleManagerException(EErrorType.INVALID_TOKEN);
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
