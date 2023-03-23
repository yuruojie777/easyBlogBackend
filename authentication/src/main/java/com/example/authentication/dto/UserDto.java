package com.example.authentication.dto;

import com.example.authentication.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String username;
    private String name;
    private byte[] avatar;
    private Role role;
}
