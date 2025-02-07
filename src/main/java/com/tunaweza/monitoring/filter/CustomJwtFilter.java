package com.tunaweza.monitoring.filter;

import java.io.IOException;
import java.time.Instant;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tunaweza.monitoring.services.CustomUserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {

    private final CustomUserDetails customUserDetails;
    @Override
    protected void doFilterInternal(
        @SuppressWarnings("null") HttpServletRequest request, 
        @SuppressWarnings("null") HttpServletResponse response, 
        @SuppressWarnings("null") FilterChain filterChain
        ) throws ServletException, IOException {
        final String authHeader =request.getHeader("Authorisation");
        String username=null;
        //String jwt=null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            //jwt= authHeader.substring(7);
            username= extractUsername(SecurityContextHolder.getContext().getAuthentication());
        }
        if(username!=null){
            UserDetails userDetails = customUserDetails.loadUserByUsername(username);
            if(isTokenExpired(SecurityContextHolder.getContext().getAuthentication())==false){
                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

     public String extractUsername(Authentication authentication){
        if(authentication instanceof JwtAuthenticationToken jwtToken){
            Jwt jwt = jwtToken.getToken();

            return jwt.getSubject();
        }
        throw new UnsupportedOperationException("not found");
    }

    public boolean isTokenExpired(Authentication authentication){
        Instant now = Instant.now();
        if(authentication instanceof JwtAuthenticationToken jwtToken){
            Jwt jwt = jwtToken.getToken();
            Instant old=jwt.getExpiresAt();
            return now.isAfter(old);
        }
        return false;
    }

}
