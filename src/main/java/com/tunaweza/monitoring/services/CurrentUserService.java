package com.tunaweza.monitoring.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tunaweza.monitoring.dto.UserDTO;
import com.tunaweza.monitoring.mapper.UserMapper;
import com.tunaweza.monitoring.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrentUserService {
    private final UserRepository userRepository;
    public UserDTO getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!authentication.isAuthenticated() || authentication==null) return null;
        return UserMapper.mapToDto(userRepository.findByUsername(authentication.getName().toString()));
    }
}
