package com.qztc.parkingmanagementsystem.domain.po;

/**
@author congyijiu
@create 2024-04-25-11:47
*/

import lombok.Data;

/**
    * 车位表
    */
@Data
public class BPark {
    /**
    * 车位ID
    */
    private Long parkId;

    /**
    * 小区ID
    */
    private Long commId;

    /**
    * 车位地址
    */
    private String parkAddr;

    /**
    * 归属业主
    */
    private Long belongBy;

    /**
    * 出租时间
    */
    private String spareTime;

    /**
    * 状态
    */
    private Integer status;

    /**
    * 备注
    */
    private String remark;

}