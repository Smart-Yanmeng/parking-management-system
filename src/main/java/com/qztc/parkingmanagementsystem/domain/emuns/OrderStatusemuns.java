package com.qztc.parkingmanagementsystem.domain.emuns;

/**
 * @author congyijiu
 * @create 2024-05-08-10:29
 */
public enum OrderStatusemuns {
    /**
     * 已创建订单
     */
    CREATED(0),
    /**
     * 已选择车位
     */
    SELECTED(1),
    /**
     * 已支付
     */
    PAID(2);

    private Integer status;

    OrderStatusemuns(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
