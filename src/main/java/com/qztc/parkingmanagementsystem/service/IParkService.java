package com.qztc.parkingmanagementsystem.service;

import com.qztc.parkingmanagementsystem.domain.dto.ParkShareDto;
import com.qztc.parkingmanagementsystem.domain.po.BPark;
import com.qztc.parkingmanagementsystem.domain.vo.ResultVo;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-26-20:23
 */
public interface IParkService {

    /**
     * 根据小区id查找停车位,并筛选出空闲的停车位
     */
    List<BPark> findParkByCommId(Long commId,Integer hour);

    /**
     * 根据小区ids查找停车位，并筛选出空闲的停车位
     */
    List<BPark> findParkByCommIds(List<Long> commIds,Integer hour);

    /**
     * 根据停车位id查找停车位
     */
    BPark findParkById(Long parkId);


    /**
     * 分享停车位
     */
    ResultVo parkShare(ParkShareDto parkShareDto);
}
