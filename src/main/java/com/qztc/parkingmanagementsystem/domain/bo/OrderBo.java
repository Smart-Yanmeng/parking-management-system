package com.qztc.parkingmanagementsystem.domain.bo;

import com.qztc.parkingmanagementsystem.domain.emuns.OrderStatusemuns;
import com.qztc.parkingmanagementsystem.domain.po.BOrder;

import java.time.LocalDateTime;

/**
 * @author congyijiu
 * @create 2024-05-08-10:23
 */
public class OrderBo {

    /**
     * 设置order公共属性
     */
    public static void setOrderCommonProperties(BOrder bOrder, Integer status) {
        bOrder.setDelFlag(0);
        bOrder.setStatus(status);
        if (bOrder.getCreateTime() == null) {
            bOrder.setCreateTime(LocalDateTime.now());
        }
        bOrder.setUpdateTime(LocalDateTime.now());
    }
}
