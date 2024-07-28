package com.qztc.parkingmanagementsystem.service.impl;

import com.qztc.parkingmanagementsystem.common.RabbitMQService;
import com.qztc.parkingmanagementsystem.domain.bo.OrderBo;
import com.qztc.parkingmanagementsystem.domain.dto.CreateOrderDto;
import com.qztc.parkingmanagementsystem.domain.dto.SettleOrderDto;
import com.qztc.parkingmanagementsystem.domain.dto.UpdateOrderDto;
import com.qztc.parkingmanagementsystem.domain.emuns.OrderStatusemuns;
import com.qztc.parkingmanagementsystem.domain.po.BOrder;
import com.qztc.parkingmanagementsystem.domain.vo.ResultVo;
import com.qztc.parkingmanagementsystem.mapper.IOrderMapper;
import com.qztc.parkingmanagementsystem.service.IOrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author congyijiu
 * @create 2024-05-08-09:52
 */
@Service
public class IOrderServiceImpl implements IOrderService {

    @Resource
    private IOrderMapper iOrderMapper;

    @Autowired
    private RabbitMQService rabbitMQService;

    @Override
    public ResultVo createOrder(CreateOrderDto createOrderDto) {
        ResultVo resultVo = checkOrderValidity(createOrderDto);
        if (resultVo != null) {
            return resultVo;
        }

        BOrder bOrder = new BOrder();
        BeanUtils.copyProperties(createOrderDto, bOrder);
        OrderBo.setOrderCommonProperties(bOrder, OrderStatusemuns.CREATED.getStatus());
        int i = iOrderMapper.insert(bOrder);
        //todo,创建订单成功后，可能需要发送消息给商家
        //todo,创建订单成功后，可能需要锁定车位

        //发送消息到延迟队列
        rabbitMQService.sendOrderMessage(bOrder.getOrderId().toString());

        return i > 0 ? ResultVo.success("创建订单成功") : ResultVo.error("创建订单失败");
    }

    @Override
    public ResultVo updateOrder(UpdateOrderDto updateOrderDto) {
        BOrder bOrder = iOrderMapper.findById(updateOrderDto.getOrderId());
        if (bOrder == null) {
            return ResultVo.error("订单不存在");
        }
        return null;
    }

    @Override
    public ResultVo settleOrder(SettleOrderDto settleOrderDto) {
        return null;
    }


    public ResultVo checkOrderValidity(@org.jetbrains.annotations.NotNull CreateOrderDto createOrderDto) {
//        // 检查订单合理性的逻辑
//        Long payId = createOrderDto.getPayId();
//        //todo,检查客户是否已有未完成订单，如果有，不允许创建新订单
//        List<BOrder> unpaidOrders = iOrderMapper.getUnpaidOrders(payId);
//        for (BOrder unpaidOrder : unpaidOrders) {
//            if (unpaidOrder.getCreateTime().plusMinutes(30).isAfter(LocalDateTime.now())) {
//                return ResultVo.error("您有未支付订单，请先支付");
//            }
//        }

        return null;
    }


}
