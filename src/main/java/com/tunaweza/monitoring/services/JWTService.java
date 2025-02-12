package com.tunaweza.monitoring.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tunaweza.monitoring.constants.JwtConstant;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final JwtServiceDecoder jwtServiceDecoder;
    private final CustomUserDetails customUserDetails;

    public Map<String, String> generateTokens(Authentication authentication) {
        return Map.of(
                "access_token", generateToken(authentication),
                "refresh_token", generateRefreshToken(authentication)
        );
    }

    public String generateToken(Authentication authentication) {
                Instant now = Instant.now();
            JwtClaimsSet claims = JwtClaimsSet.builder()
                      .issuer("self")
                     .issuedAt(now)
                     .claim("username", ((UserDetails)authentication.getPrincipal()).getUsername())
                     .claim("roles",
                     authentication.getAuthorities().stream().map(k->k.getAuthority()).collect(Collectors.toList()))
                     .claim("scope",converToString(" ",
                     authentication.getAuthorities().stream().map(k->k.getAuthority()).collect(Collectors.toList())))
                      .expiresAt(now.plus(1, ChronoUnit.DAYS))
                      .subject(authentication.getName())
                      .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    private String generateRefreshToken(Authentication authentication) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .claim("username", ((UserDetails)authentication.getPrincipal()).getUsername())
                .claim("token_type", "refresh")
                .expiresAt(now.plus(7, ChronoUnit.DAYS))
                .subject(authentication.getName())
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    public String refreshAccessToken(String refreshToken) {
        Claims claims = jwtServiceDecoder.extractAllClaims(refreshToken, JwtConstant.SECRET_JWT_KEY);
        if (!jwtServiceDecoder.isTokenNotExpired(claims.getExpiration())) {
            throw new IllegalArgumentException("Refresh token expiré");
        }

        if (!"refresh".equals(claims.get("token_type"))) {
            throw new IllegalArgumentException("Token invalide");
        }

        String username = claims.get("username", String.class);
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        return generateToken(authentication);
    }

    public String converToString(String delimiter, List<String> object){
        return object.stream().reduce((x,y)->x+delimiter+y).get();
    }
}
