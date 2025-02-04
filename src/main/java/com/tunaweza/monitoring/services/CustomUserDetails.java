package com.tunaweza.monitoring.services;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tunaweza.monitoring.model.User;
import com.tunaweza.monitoring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService{

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if(user==null) throw new UsernameNotFoundException("user not found with username:"+username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }

}
