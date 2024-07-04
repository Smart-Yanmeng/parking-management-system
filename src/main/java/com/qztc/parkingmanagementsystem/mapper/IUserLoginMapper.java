package com.qztc.parkingmanagementsystem.mapper;

import com.qztc.parkingmanagementsystem.domain.dto.UserLoginDto;
import com.qztc.parkingmanagementsystem.domain.po.BUserPo;

public interface IUserLoginMapper {

    int reg(BUserPo bUserPo);

    BUserPo login(UserLoginDto userLoginDto);

    BUserPo selectUser(String username);
}
