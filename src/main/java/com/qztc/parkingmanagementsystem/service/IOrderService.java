package com.qztc.parkingmanagementsystem.service;

import com.qztc.parkingmanagementsystem.domain.dto.CreateOrderDto;
import com.qztc.parkingmanagementsystem.domain.dto.SettleOrderDto;
import com.qztc.parkingmanagementsystem.domain.dto.UpdateOrderDto;
import com.qztc.parkingmanagementsystem.domain.vo.ResultVo;

/**
 * @author congyijiu
 * @create 2024-05-08-09:52
 */
public interface IOrderService {
    ResultVo createOrder(CreateOrderDto createOrderDto);

    ResultVo updateOrder(UpdateOrderDto updateOrderDto);

    ResultVo settleOrder(SettleOrderDto settleOrderDto);
}
