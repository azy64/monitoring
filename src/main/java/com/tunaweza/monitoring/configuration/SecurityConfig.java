package com.tunaweza.monitoring.configuration;

import javax.crypto.spec.SecretKeySpec;

//import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.tunaweza.monitoring.filter.CustomJwtFilter;
import com.tunaweza.monitoring.services.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetails userDetails;
    private final String jwtKey ="FxgP5G12tlyUwcZW/Xafb1BX9isM8Lw1eKxeSv3vtngnxqsOWl6saGW+1aO40Fpq";
    //@Value("${issue-uri}")
    //private final String issuerUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .httpBasic(Customizer.withDefaults())
        .csrf(csrf-> csrf.disable())
        .authorizeHttpRequests(auth->{
            auth.requestMatchers("/admins/**").authenticated()
            .requestMatchers("/users/**").authenticated()
            //.//hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
            //.hasRole("ADMIN")
            //.hasAnyRole("ADMIN","USER")
            .requestMatchers("/agents/**").authenticated()
            //.hasRole("AGENT")
            .anyRequest().permitAll();
        })
        .addFilterBefore(new CustomJwtFilter(userDetails), UsernamePasswordAuthenticationFilter.class)
        .oauth2ResourceServer(oauth2 -> oauth2
        .jwt(Customizer.withDefaults())
        )

        .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception
    {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(this.userDetails).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKey = new SecretKeySpec(this.jwtKey.getBytes(), 0, this.jwtKey.getBytes().length,"RSA");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS256).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
    }
}
