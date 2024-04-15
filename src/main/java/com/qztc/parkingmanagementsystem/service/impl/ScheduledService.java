package com.qztc.parkingmanagementsystem.service.impl;

import com.qztc.parkingmanagementsystem.util.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author congyijiu
 * @create 2024-04-15-14:41
 */

@Component
@Slf4j
public class ScheduledService {


    //test,每5秒更新一次
    //@Scheduled(cron = "0/5 * * * * ?")
    //定时任务，每天凌晨0点更新地图上的点集合
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateMapPoints() {
        log.info("更新地图上的点集合");
        //todo: 更新地图上的点集合
        //MapUtil.setPoints();
    }
}
