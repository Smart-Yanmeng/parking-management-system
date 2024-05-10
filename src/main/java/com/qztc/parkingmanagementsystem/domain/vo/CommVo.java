package com.qztc.parkingmanagementsystem.domain.vo;

import com.qztc.parkingmanagementsystem.domain.po.BCommPo;
import com.qztc.parkingmanagementsystem.domain.po.BPark;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-04-28-20:41
 */
@Data
public class CommVo {
    private Long commId;

    private String commName;

    private String addr;

    private double geoX;

    private double geoY;

    private List<BPark> parkList;

    //车位总数
    private Integer count;

    //最高价格
    private Integer maxPrice;

    //最低价格
    private Integer minPrice;

    //最低5个价格平均数
    private Integer minPricesAve;






}
