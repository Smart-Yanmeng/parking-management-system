package com.qztc.parkingmanagementsystem.bo;

import lombok.Data;

@Data
public class UserLoginBo {

    private Long commId;

    private Integer userType;

    private String username;

    private String password;

    private String nickName;

    private Integer sex;
}
