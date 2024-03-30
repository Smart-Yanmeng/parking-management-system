package com.qztc.parkingmanagementsystem.converter;

import com.qztc.parkingmanagementsystem.bo.UserLoginBo;
import com.qztc.parkingmanagementsystem.po.BUserPo;
import com.qztc.parkingmanagementsystem.util.ClassConvertUtil;
import org.springframework.beans.BeanUtils;

public class UserLoginBoConvert implements ClassConvertUtil<UserLoginBo, BUserPo> {
    @Override
    public BUserPo convert(UserLoginBo userLoginBo) {
        BUserPo bUserPo = new BUserPo();
        BeanUtils.copyProperties(userLoginBo, bUserPo);

        return bUserPo;
    }
}
