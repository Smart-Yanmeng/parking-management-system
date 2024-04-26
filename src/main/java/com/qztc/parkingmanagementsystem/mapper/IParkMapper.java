package com.qztc.parkingmanagementsystem.mapper;

import com.qztc.parkingmanagementsystem.domain.po.BPark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-26-10:49
 */
public interface IParkMapper {

    //根据小区id查询所有停车位
    List<BPark> selectAllByCommunityId(Long communityId);

    //插入停车位
    int insertPark(BPark park);


    //查询所有停车位
    List<BPark> selectAll();

    /**
     * 根据小区ids查询所有停车位
     */
    List<BPark> selectAllByCommunityIds(@Param("communityIds")List<Long> communityIds);

}
