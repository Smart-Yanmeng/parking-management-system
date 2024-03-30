package com.qztc.parkingmanagementsystem.service;

import com.qztc.parkingmanagementsystem.bo.UserRegBo;
import com.qztc.parkingmanagementsystem.dto.UserLoginDto;

public interface IUserService {

    int reg(UserRegBo userRegBo);

    String login(UserLoginDto userLoginDto);
}
