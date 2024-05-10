package com.qztc.parkingmanagementsystem.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2024-04-28-20:53
 */

@Data
public class CommParkDto {
    @NotNull(message = "x坐标不能为空")
    private double x;
    @NotNull(message = "y坐标不能为空")
    private double y;
    @NotNull(message = "预计停车时长不能为空")
    private Integer parkTime;
}
