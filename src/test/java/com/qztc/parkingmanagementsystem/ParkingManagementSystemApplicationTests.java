package com.qztc.parkingmanagementsystem;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qztc.parkingmanagementsystem.domain.po.BPark;
import com.qztc.parkingmanagementsystem.mapper.IParkMapper;
import com.qztc.parkingmanagementsystem.util.RandomScheduleGenerator;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.*;

@SpringBootTest
@Slf4j
@EnableRabbit
class ParkingManagementSystemApplicationTests {

    @Resource
    private IParkMapper iParkMapper;

    @Test
    void contextLoads() {
    }


    @Test
    void jsontest() {
    }


    //插入停车位测试,查询所有停车位测试
    @Test
    void insertParktest() {
        for (int i = 0; i < 30; i++) {
            BPark bPark = new BPark();
            bPark.setCommId(1L);
            bPark.setParkAddr("A" + i);
            bPark.setBelongBy(1L);
            String spaceTime = RandomScheduleGenerator.generateRandomSchedule();
            bPark.setSpareTime(spaceTime);
            bPark.setStatus(0);
            bPark.setRemark("这是一个测试车位" + i);
            iParkMapper.insertPark(bPark);
            log.info("插入成功A" + i + "车位");
        }
        List<BPark> bParks = iParkMapper.selectAll();
        for (BPark park : bParks) {
            log.info(park.toString());
        }
    }

    //查询所有停车位测试，并判断是否在空闲时间
    @Test
    void selectAllParkTest() {
        List<BPark> bParks = iParkMapper.selectAll();

//        Map<String,String> testmap = JSON.parseObject("{'a':'a'}", new TypeReference<Map<String, String>>(){});

        long startthetest = System.currentTimeMillis();
        for (BPark park : bParks) {
            long startitem = System.currentTimeMillis();

            //获取当前日期
            Date date = new Date();
            //获取星期几
            String week = date.toString().split(" ")[0];
            //获取当前时间
            LocalTime currentTime = LocalTime.now().withNano(0);
            //获取空闲时间
            String spareTime = park.getSpareTime();
            //解析json
            Map<String, Map<String, String>> map = JSON.parseObject(spareTime, new TypeReference<Map<String, Map<String, String>>>(){});
            //获取当天的空闲时间
            Map<String,String> today = map.get(week);

            log.info("今天的空闲时间：" + today);
            //获取开始时间
            String start = today.get("start");
            //转化为时间
            LocalTime startTime = LocalTime.parse(start);
            //获取结束时间,转化为时间
            LocalTime endTime = LocalTime.parse(today.get("end"));

            //判断是否在空闲时间
            if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)){
                log.info("在空闲时间");
            } else {
                log.info("不在空闲时间");
            }

            long enditem = System.currentTimeMillis();

            log.info("item测试时间：" + (enditem - startitem) + "ms");
        }
        long endthetest = System.currentTimeMillis();

        log.info("测试时间：" + (endthetest - startthetest) + "ms");

        /*
         * 测试时间：130ms 当没有初始化json和map时
         * 测试时间：30ms 初始化json和map时
         */

    }



}
