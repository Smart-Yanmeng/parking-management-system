package com.qztc.parkingmanagementsystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qztc.parkingmanagementsystem.domain.dto.ParkShareDto;
import com.qztc.parkingmanagementsystem.domain.po.BPark;
import com.qztc.parkingmanagementsystem.domain.vo.ResultVo;
import com.qztc.parkingmanagementsystem.mapper.IParkMapper;
import com.qztc.parkingmanagementsystem.scache.WeekMap;
import com.qztc.parkingmanagementsystem.service.IParkService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

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

    @Override
    public ResultVo parkShare(ParkShareDto parkShareDto) {
        String spareTime = strToJSONstr(parkShareDto.getSpareTime());
        BPark bPark = new BPark();
        BeanUtils.copyProperties(parkShareDto, bPark);
        bPark.setSpareTime(spareTime);
        int i = iParkMapper.insert(bPark);
        return i > 0 ? ResultVo.success("分享成功") : ResultVo.error("分享失败");
    }

    private String strToJSONstr(String spareTime) {
        //todo,可能需要转换，暂时不做
        return spareTime;
    }

    /**
     * 筛选空闲的停车位
     */
    private List<BPark> filterSpacePark(List<BPark> bParks, Integer hour) {
        List<BPark> res = new ArrayList<>();
        for (BPark park : bParks) {
            //获取当前日期
            Date date = new Date();

            // 获取当前日期
            LocalDate currentDate = LocalDate.now();

            // 获取当前星期几
            Integer week = currentDate.getDayOfWeek().ordinal();
            String weekStr = WeekMap.getWeekStr(week);


            //获取空闲时间
            String spareTime = park.getSpareTime();
            //解析json
            Map<String, Map<String, String>> map = JSON.parseObject(spareTime, new TypeReference<Map<String, Map<String, String>>>() {
            });
            //获取当天的空闲时间
            Map<String, String> today = map.get(weekStr);
            //获取当前时间
            LocalTime currentTime = LocalTime.now().withNano(0);
            //将时间加上hour
            LocalTime toTime = currentTime.plusHours(hour);

            //获取开始时间,转化为时间
            LocalTime startTime = LocalTime.parse(today.get("start"));
            //获取结束时间,转化为时间
            LocalTime endTime = LocalTime.parse(today.get("end"));

            //判断从当前时间到hour后是否在空闲时间
            if (currentTime.isAfter(startTime) && toTime.isBefore(endTime)) {
                park.setSpareTime(JSON.toJSONString(today));
                res.add(park);
            }
        }
        return res;
    }



}
