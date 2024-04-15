package com.qztc.parkingmanagementsystem.controller;

import com.qztc.parkingmanagementsystem.domain.dto.Point;
import com.qztc.parkingmanagementsystem.domain.vo.ResultVo;
import com.qztc.parkingmanagementsystem.util.MapUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-15-11:48
 */

@RestController
@RequestMapping("/test")
public class TestController {

        @RequestMapping("/findNearestPoints")
        public ResultVo hello() {
            List<Point> nearestPoints = MapUtil.findNearestPoints(new Point(0, 0), 3);
            for (Point point : nearestPoints) {
                System.out.println(point.x + " " + point.y);
            }
            return ResultVo.success(nearestPoints);
        }

}
