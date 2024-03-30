package com.qztc.parkingmanagementsystem.mapper;

import com.qztc.parkingmanagementsystem.dto.UserLoginDto;
import com.qztc.parkingmanagementsystem.po.BUserPo;

public interface IUserLoginMapper {

    int reg(BUserPo bUserPo);

    BUserPo login(UserLoginDto userLoginDto);
}
