package com.qztc.parkingmanagementsystem.controller;

import com.qztc.parkingmanagementsystem.domain.po.Point;
import com.qztc.parkingmanagementsystem.domain.po.BCommPo;
import com.qztc.parkingmanagementsystem.domain.vo.ResultVo;
import com.qztc.parkingmanagementsystem.scache.CommMap;
import com.qztc.parkingmanagementsystem.service.ICommService;
import com.qztc.parkingmanagementsystem.util.MapUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-15-11:48
 */
@RestController
@RequestMapping("/test")
@Tag(name = "测试接口")
@Slf4j
public class TestController {

    @Resource
    private ICommService iCommService;


    @GetMapping("/findNearestPoints")
    @Operation(summary = "查找最近的点")
    public ResultVo<List<Point>> findNearestPoints() {
        List<Point> nearestPoints = MapUtil.findNearestPoints(CommMap.getPoints(),new Point(0, 0), 5);
        nearestPoints.forEach(p -> log.info("x:{} y:{}", p.x, p.y));
        return ResultVo.success(nearestPoints);
    }

    @GetMapping("/findNearestComm")
    @Operation(summary = "查找最近的小区")
    public ResultVo<List<BCommPo>> findNearestComm() {
        List<BCommPo> nearestComm = iCommService.findNearestComm(new Point(0, 0), 5);
        return ResultVo.success(nearestComm);
    }



}
