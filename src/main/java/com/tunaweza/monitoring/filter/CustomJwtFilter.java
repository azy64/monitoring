package com.tunaweza.monitoring.filter;

import java.io.IOException;
import java.util.Date;

import org.apache.catalina.filters.RequestFilter;
import org.apache.juli.logging.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.tunaweza.monitoring.constants.JwtConstant;
import com.tunaweza.monitoring.services.CustomUserDetails;
import com.tunaweza.monitoring.services.JwtServiceDecoder;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomJwtFilter extends RequestFilter {

    private final CustomUserDetails customUserDetails;
    private final JwtServiceDecoder jwtserviceDecoder;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        final String authHeader = httpRequest.getHeader("Authorization");
        String username=null;
        Date expiredDate=null;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            String jwt= authHeader.substring(7);
            Claims claims= jwtserviceDecoder.extractAllClaims(jwt,JwtConstant.SECRET_JWT_KEY);
            username= claims.get("username", String.class);
            expiredDate = claims.getExpiration();
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            
            UserDetails userDetails = customUserDetails.loadUserByUsername(username);
            
            if(jwtserviceDecoder.isTokenNotExpired(expiredDate)){
                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    protected Log getLogger() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
