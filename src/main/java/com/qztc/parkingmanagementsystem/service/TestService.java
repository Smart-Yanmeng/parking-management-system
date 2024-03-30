package com.qztc.parkingmanagementsystem.service;

import com.qztc.parkingmanagementsystem.mapper.ITestMapper;
import com.qztc.parkingmanagementsystem.po.BUserPo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Resource
    private ITestMapper testMapper;

    public List<BUserPo> test() {
        return testMapper.queryAll();
    }
}
