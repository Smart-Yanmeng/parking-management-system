package com.qztc.parkingmanagementsystem.dto;

import lombok.Data;

@Data
public class UserRegDto {

    private Long commId;

    private Integer userType;

    private String username;

    private String password;

    private String nickName;

    private Integer sex;
}
