package com.qztc.parkingmanagementsystem.service.impl;

import com.qztc.parkingmanagementsystem.domain.dto.Point;
import com.qztc.parkingmanagementsystem.domain.po.BCommPo;
import com.qztc.parkingmanagementsystem.mapper.ICommMapper;
import com.qztc.parkingmanagementsystem.service.ICommService;
import com.qztc.parkingmanagementsystem.util.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-17-11:30
 */

@Service
@Slf4j
public class ICommServiceImpl implements ICommService {

    @Autowired
    private ICommMapper iCommMapper;

    @Override
    public List<BCommPo> findNearestComm(Point point, int num) {
        List<Point> nearestPoints = MapUtil.findNearestPoints(point, num);
        List<Long> ids = new ArrayList<>();
        for (Point p : nearestPoints) {
            log.info(p.x + " " + p.y);
            ids.add(p.id);
        }
        List<BCommPo> bCommPos = iCommMapper.selectByIds(ids);
        return bCommPos;
    }
}
