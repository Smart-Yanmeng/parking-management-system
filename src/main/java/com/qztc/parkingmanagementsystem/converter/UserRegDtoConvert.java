package com.qztc.parkingmanagementsystem.converter;

import com.qztc.parkingmanagementsystem.bo.UserRegBo;
import com.qztc.parkingmanagementsystem.dto.UserRegDto;
import com.qztc.parkingmanagementsystem.util.ClassConvertUtil;
import org.springframework.beans.BeanUtils;

public class UserRegDtoConvert implements ClassConvertUtil<UserRegDto, UserRegBo> {
    @Override
    public UserRegBo convert(UserRegDto userRegDto) {
        UserRegBo userRegBo = new UserRegBo();
        BeanUtils.copyProperties(userRegDto, userRegBo);

        return userRegBo;
    }
}
