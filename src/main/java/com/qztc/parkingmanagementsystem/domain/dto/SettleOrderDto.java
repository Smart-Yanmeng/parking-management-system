package com.qztc.parkingmanagementsystem.domain.dto;

import java.time.LocalDateTime;

/**
 * @author congyijiu
 * @create 2024-05-08-09:47
 */
public class SettleOrderDto {
    /**
     * 客户ID
     */
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
     * 停车时长
     */
    private Integer parkTime;

    /**
     * 租金
     */
    private Double rent;

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
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}
