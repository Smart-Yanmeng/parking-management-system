package com.qztc.parkingmanagementsystem.service.impl;

import com.qztc.parkingmanagementsystem.domain.dto.Point;
import com.qztc.parkingmanagementsystem.util.MapUtil;
import com.qztc.parkingmanagementsystem.mapper.ICommMapper;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-15-14:41
 */

@Component
@Slf4j
public class ScheduledService {

    @Resource
    private ICommMapper iCommMapper;

//    test,每5秒更新一次
//    @Scheduled(cron = "0/5 * * * * ?")

    //初始化方法，项目启动时更新地图上的点集合
    //todo:开发时可以先注释，不然每次启动都会更新
    @PostConstruct
    //定时任务，每天凌晨0点更新地图上的点集合
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateMapPoints() {
        log.info("更新地图上的点集合");
        List<Point> points = iCommMapper.selectAll();
        MapUtil.setPoints(points);
        log.info("更新地图上的点集合成功");
    }
}
