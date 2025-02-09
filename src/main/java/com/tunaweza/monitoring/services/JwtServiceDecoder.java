package com.tunaweza.monitoring.services;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceDecoder {

     public Claims extractAllClaims(String token,String secretJwtKey){
        Claims claims = Jwts.parserBuilder().setSigningKey(secretJwtKey.getBytes())
        .build().parseClaimsJws(token).getBody();
        return claims;
        
    }
    public String extractclaimByName(Claims claims,String claimName){
        return claims.get(claimName,String.class);
    }

    public boolean isTokenNotExpired(Date expiredDate){
        return expiredDate.after((new Date()));
    }
}
