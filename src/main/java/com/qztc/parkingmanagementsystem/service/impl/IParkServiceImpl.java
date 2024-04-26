package com.qztc.parkingmanagementsystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qztc.parkingmanagementsystem.domain.po.BPark;
import com.qztc.parkingmanagementsystem.mapper.IParkMapper;
import com.qztc.parkingmanagementsystem.service.IParkService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author congyijiu
 * @create 2024-04-26-20:23
 */
@Service
@Slf4j
public class IParkServiceImpl implements IParkService {

    @Resource
    private IParkMapper iParkMapper;

    @Override
    public List<BPark> findParkByCommId(Long commId, Integer hour) {
        List<BPark> bParks = iParkMapper.selectAllByCommunityId(commId);
        return filterSpacePark(bParks, hour);
    }

    @Override
    public List<BPark> findParkByCommIds(List<Long> commIds, Integer hour) {
        List<BPark> bParks = iParkMapper.selectAllByCommunityIds(commIds);
        return filterSpacePark(bParks, hour);
    }

    @Override
    public BPark findParkById(Long parkId) {
        return iParkMapper.selectById(parkId);
    }

    /**
     * 筛选空闲的停车位
     */
    private List<BPark> filterSpacePark(List<BPark> bParks, Integer hour) {
        List<BPark> res = new ArrayList<>();
        for (BPark park : bParks) {
            //获取当前日期
            Date date = new Date();
            //获取星期几
            String week = date.toString().split(" ")[0];
            //获取空闲时间
            String spareTime = park.getSpareTime();
            //解析json
            Map<String, Map<String, String>> map = JSON.parseObject(spareTime, new TypeReference<Map<String, Map<String, String>>>(){});
            //获取当天的空闲时间
            Map<String,String> today = map.get(week);

            //获取当前时间
            LocalTime currentTime = LocalTime.now().withNano(0);
            //将时间加上hour
            LocalTime toTime = currentTime.plusHours(hour);

            //获取开始时间,转化为时间
            LocalTime startTime = LocalTime.parse(today.get("start"));
            //获取结束时间,转化为时间
            LocalTime endTime = LocalTime.parse(today.get("end"));

            //判断从当前时间到hour后是否在空闲时间
            if (currentTime.isAfter(startTime) && toTime.isBefore(endTime)){
                res.add(park);
            }

        }
        return res;
    }



}
