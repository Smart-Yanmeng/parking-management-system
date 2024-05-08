package com.qztc.parkingmanagementsystem.service.impl;

import com.qztc.parkingmanagementsystem.domain.dto.Point;
import com.qztc.parkingmanagementsystem.domain.po.BCommPo;
import com.qztc.parkingmanagementsystem.scache.CommMap;
import com.qztc.parkingmanagementsystem.service.ICommService;
import com.qztc.parkingmanagementsystem.util.MapUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.qztc.parkingmanagementsystem.mapper.ICommMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author congyijiu
 * @create 2024-04-17-11:30
 */

@Service
@Slf4j
public class ICommServiceImpl implements ICommService {

    @Resource
    private ICommMapper iCommMapper;

    @Override
    public List<BCommPo> findNearestComm(Point point, int num) {
        List<Point> nearestPoints = MapUtil.findNearestPoints(CommMap.getPoints(),point, num);
        List<Long> ids = nearestPoints.stream()
                .peek(p -> log.info(p.x + " " + p.y))
                .map(p -> p.id)
                .collect(Collectors.toList());
        return iCommMapper.selectByIds(ids);
    }
}
