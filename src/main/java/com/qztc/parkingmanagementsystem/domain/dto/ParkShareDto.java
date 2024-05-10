package com.qztc.parkingmanagementsystem.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author congyijiu
 * @create 2024-05-08-09:30
 */

@Data
public class ParkShareDto {
    /**
     * 小区ID
     */
    @NotNull(message = "小区id不能为空")
    private Long commId;

    /**
     * 车位地址
     */
    @NotNull(message = "车位地址不能为空")
    private String parkAddr;

    /**
     * 归属业主
     */
    @NotNull(message = "归属业主不能为空")
    private Long belongBy;

    /**
     * 出租时间
     */
    @NotNull(message = "出租时间不能为空")
    private String spareTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 租金
     */
    @NotNull(message = "租金不能为空")
    private BigDecimal rent;
}
