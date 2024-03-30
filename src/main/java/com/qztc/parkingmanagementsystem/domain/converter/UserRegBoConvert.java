package com.qztc.parkingmanagementsystem.domain.converter;

import com.qztc.parkingmanagementsystem.domain.bo.UserRegBo;
import com.qztc.parkingmanagementsystem.domain.po.BUserPo;
import com.qztc.parkingmanagementsystem.util.ClassConvertUtil;
import org.springframework.beans.BeanUtils;

public class UserRegBoConvert implements ClassConvertUtil<UserRegBo, BUserPo> {
    @Override
    public BUserPo convert(UserRegBo userRegBo) {
        BUserPo bUserPo = new BUserPo();
        BeanUtils.copyProperties(userRegBo, bUserPo);

        return bUserPo;
    }
}
