package com.qztc.parkingmanagementsystem.mapper;

import com.qztc.parkingmanagementsystem.domain.po.BOrder;

/**
 * @author congyijiu
 * @create 2024-04-26-10:49
 */
public interface IOrderMapper {
    int insert(BOrder bOrder);

    BOrder findById(Long orderId);
}
