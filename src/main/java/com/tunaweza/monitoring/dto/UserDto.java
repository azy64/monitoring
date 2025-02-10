package com.tunaweza.monitoring.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {

    private UUID id;
    private String phoneNumber;
    private String address;
    private String pictureUser;
}
