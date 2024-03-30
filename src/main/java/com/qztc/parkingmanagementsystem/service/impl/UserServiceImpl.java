package com.qztc.parkingmanagementsystem.service.impl;

import com.qztc.parkingmanagementsystem.domain.bo.UserRegBo;
import com.qztc.parkingmanagementsystem.domain.converter.UserRegBoConvert;
import com.qztc.parkingmanagementsystem.domain.dto.UserLoginDto;
import com.qztc.parkingmanagementsystem.mapper.IUserLoginMapper;
import com.qztc.parkingmanagementsystem.domain.po.BUserPo;
import com.qztc.parkingmanagementsystem.service.IUserService;
import com.qztc.parkingmanagementsystem.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserLoginMapper userLoginMapper;

    @Override
    public int reg(UserRegBo userRegBo) {

        BUserPo user = new UserRegBoConvert().convert(userRegBo);

        if (user != null) return userLoginMapper.reg(user);
        else return -1;
    }

    @Override
    public String login(UserLoginDto userLoginDto) {

        BUserPo user = userLoginMapper.login(userLoginDto);

        if (user == null) return null;
        else return JwtUtil.createToken(user.getUserId());
    }
}
