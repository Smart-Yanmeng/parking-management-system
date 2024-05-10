package com.qztc.parkingmanagementsystem.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author congyijiu
 * @create 2024-05-08-09:40
 */


@Data
public class CreateOrderDto {

    /**
     * 客户ID
     */
    @NotNull(message = "客户ID不能为空")
    private Long payId;

    /**
     * 商家ID
     */
    private Long recvId;

    /**
     * 车位ID
     */
    private Long parkId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标志
     */
    private Integer delFlag;

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
