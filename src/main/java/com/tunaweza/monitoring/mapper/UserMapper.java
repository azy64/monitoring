package com.tunaweza.monitoring.mapper;

import com.tunaweza.monitoring.dto.UserDTO;
import com.tunaweza.monitoring.model.User;

public class UserMapper {
    public static UserDTO mapToDto(User user){
        if(user==null)return null;
        return UserDTO.builder()
            .birth(user.getBirth())
            .id(user.getId())
            .nom(user.getNom())
            .prenom(user.getPrenom())
            .typeUser(user.getTypeUser() != null ? user.getTypeUser().getLabelling() : null)
            .address(user.getAddress())
            .phoneNumber(user.getPhoneNumber())
            .pictureUser(user.getPictureUser())
            .employer(CompanyMapper.mapToDto(user.getEmployer()))
            .activated(user.getActivated())
            .isUsingMfa(user.getIsUsingMfa())
            .companies(user.getCompanies()!=null?
            user.getCompanies().stream().map(company->CompanyMapper.mapToDto(company)).toList()
            :null)
        .build();
    }

    public static User mapToEntity(UserDTO userDTO){
            if(userDTO==null)return null;
            User user = new User();
            user.setId(userDTO.getId());
            user.setNom(userDTO.getNom());
            user.setBirth(userDTO.getBirth());
            user.setActivated(userDTO.getActivated());
            user.setAddress(userDTO.getAddress());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setPictureUser(userDTO.getPictureUser());
            user.setIsUsingMfa(userDTO.getIsUsingMfa());
            return user;
    }

}
