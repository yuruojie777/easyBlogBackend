package com.example.authentication.vo;


import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;
@Builder
@Data
public class UserModel {
    private UUID id;
    private String name;
    private String email;
    private Date birthday;
    private String intro;
    private String websiteUrl;
    private byte[] avatar;
}
