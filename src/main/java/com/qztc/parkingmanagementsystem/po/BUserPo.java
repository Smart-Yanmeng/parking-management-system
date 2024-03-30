package com.qztc.parkingmanagementsystem.po;

import lombok.Data;

@Data
public class BUserPo {

    private Long userId;

    private Long commId;

    private Integer userType;

    private String username;

    private String password;

    private String nickName;

    private Integer sex;

    private Integer phoneNumber;

    private String email;

    private String loginIp;

    private String loginDate;

    private String regTime;

    private String updateTime;

    private String delFlag;

    private Long delBy;

    private Integer status;

    private String remark;
}
