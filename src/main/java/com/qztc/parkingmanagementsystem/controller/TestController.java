package com.qztc.parkingmanagementsystem.controller;

import com.qztc.parkingmanagementsystem.domain.dto.Point;
import com.qztc.parkingmanagementsystem.domain.po.BCommPo;
import com.qztc.parkingmanagementsystem.domain.vo.ResultVo;
import com.qztc.parkingmanagementsystem.service.ICommService;
import com.qztc.parkingmanagementsystem.util.MapUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TestController {

    @Autowired
    private ICommService iCommService;

    @GetMapping("/findNearestPoints")
    @Operation(summary = "查找最近的点")
    public ResultVo findNearestPoints() {
        List<Point> nearestPoints = MapUtil.findNearestPoints(new Point(0, 0), 5);
        for (Point point : nearestPoints) {
            System.out.println(point.x + " " + point.y);
        }
        return ResultVo.success(nearestPoints);
    }

    @GetMapping("/findNearestComm")
    @Operation(summary = "查找最近的小区")
    public ResultVo findNearestComm() {
        List<BCommPo> nearestComm = iCommService.findNearestComm(new Point(0, 0), 5);
        return ResultVo.success(nearestComm);
    }

}
