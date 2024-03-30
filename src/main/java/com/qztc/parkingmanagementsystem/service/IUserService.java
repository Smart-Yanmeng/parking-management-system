package com.qztc.parkingmanagementsystem.service;

import com.qztc.parkingmanagementsystem.domain.bo.UserRegBo;
import com.qztc.parkingmanagementsystem.domain.dto.UserLoginDto;

public interface IUserService {

    int reg(UserRegBo userRegBo);

    String login(UserLoginDto userLoginDto);
}
