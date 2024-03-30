package com.qztc.parkingmanagementsystem.controller;

import com.qztc.parkingmanagementsystem.converter.UserRegDtoConvert;
import com.qztc.parkingmanagementsystem.dto.UserLoginDto;
import com.qztc.parkingmanagementsystem.dto.UserRegDto;
import com.qztc.parkingmanagementsystem.service.impl.UserServiceImpl;
import com.qztc.parkingmanagementsystem.util.EncUtil;
import com.qztc.parkingmanagementsystem.vo.ResultVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parking/user")
public class UserController {

    @Resource
    UserServiceImpl userService;

    @CrossOrigin
    @PostMapping("/register")
    public ResultVo register(@RequestBody UserRegDto userRegDto) {

        userRegDto.setPassword(EncUtil.MD5(userRegDto.getPassword()));
        int res = userService.reg(new UserRegDtoConvert().convert(userRegDto));

        return res == 1 ? ResultVo.success("注册成功") : ResultVo.error("注册失败");
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResultVo login(@RequestBody UserLoginDto userLoginDto) {

        userLoginDto.setPassword(EncUtil.MD5(userLoginDto.getPassword()));
        String token = userService.login(userLoginDto);

        return token != null ? ResultVo.success(token) : ResultVo.error("登录失败");
    }
}
