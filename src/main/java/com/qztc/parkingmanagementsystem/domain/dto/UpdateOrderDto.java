package com.qztc.parkingmanagementsystem.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author congyijiu
 * @create 2024-05-08-09:46
 */
@Data
public class UpdateOrderDto {

    /**
     * 订单ID
     */
    @NotNull(message = "订单ID不能为空")
    private Long orderId;

    /**
     * 商家ID
     */
    private Long recvId;

    /**
     * 车位ID
     */
    private Long parkId;

    /**
     * 状态
     * 0: 已创建订单
     * 1: 已选择车位
     * 2: 已支付
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}
