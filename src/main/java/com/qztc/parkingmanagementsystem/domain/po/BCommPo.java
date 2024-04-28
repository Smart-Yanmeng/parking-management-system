package com.qztc.parkingmanagementsystem.domain.po;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author congyijiu
 * @create 2024-04-22-14:55
 * 社区表
 */
@Data
public class BCommPo {
    /**
     * 社区ID
     */
    private Long commId;

    /**
     * 社区名称
     */
    private String commName;

    /**
     * 地址
     */
    private String addr;

    /**
     * x坐标
     */
    private Double geoX;

    /**
     * y坐标
     */
    private Double geoY;

    /**
     * 总停车位数量
     */
    private Integer parkNum;

    /**
     * 管理员ID
     */
    private Long managerId;

    /**
     * 注册日期
     */
    private LocalDate regTime;

    /**
     * 更新时间
     */
    private LocalDate updateTime;

    /**
     * 更新人
     */
    private LocalDate updateBy;

    /**
     * 删除人
     */
    private Integer delFlag;

    /**
     * 状态
     */
    private Integer status;
}