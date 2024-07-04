package com.qztc.parkingmanagementsystem.service;

import com.qztc.parkingmanagementsystem.domain.po.Point;
import com.qztc.parkingmanagementsystem.domain.po.BCommPo;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-17-11:30
 */
public interface ICommService {

    /**
     * 查找最近的小区
     * @param point 坐标
     * @param num 数量
     * @return 小区列表
     */
    List<BCommPo> findNearestComm(Point point, int num);

    /**
     * 模糊查询小区
     * @return 小区列表
     */
    List<BCommPo> selectCommByKey(String key);
}
