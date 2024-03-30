package com.qztc.parkingmanagementsystem.controller;

import com.qztc.parkingmanagementsystem.po.BUserPo;
import com.qztc.parkingmanagementsystem.service.TestService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    @RequestMapping("/hello")
    public List<BUserPo> hello() {
        return testService.test();
    }
}
