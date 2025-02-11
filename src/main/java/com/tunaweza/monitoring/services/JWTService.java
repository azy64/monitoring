package com.tunaweza.monitoring.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTService {

    private final JwtEncoder jwtEncoder;

    public Map<String, String> generateToken(Authentication authentication) {
                Instant now = Instant.now();
                String role =  authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
            JwtClaimsSet claims = JwtClaimsSet.builder()
                      .issuer("self")
                     .issuedAt(now)
                     .claim("username", ((UserDetails)authentication.getPrincipal()).getUsername())
                     .claim("roles",role)
                      .expiresAt(now.plus(1, ChronoUnit.DAYS))
                      .subject(authentication.getName())

                      .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        String token = this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return Map.of("Access-token", token);
    }
}
