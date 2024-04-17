package com.qztc.parkingmanagementsystem.service;

import com.qztc.parkingmanagementsystem.domain.dto.Point;
import com.qztc.parkingmanagementsystem.domain.po.BCommPo;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-17-11:30
 */


public interface ICommService {

    List<BCommPo> findNearestComm(Point point, int num);
}
