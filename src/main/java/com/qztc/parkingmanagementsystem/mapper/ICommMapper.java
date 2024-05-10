package com.qztc.parkingmanagementsystem.mapper;


import com.qztc.parkingmanagementsystem.domain.po.Point;
import com.qztc.parkingmanagementsystem.domain.po.BCommPo;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-15-16:06
 */
public interface ICommMapper {
    List<Point> selectAll();

    List<BCommPo> selectByIds(List<Long> ids);
}
