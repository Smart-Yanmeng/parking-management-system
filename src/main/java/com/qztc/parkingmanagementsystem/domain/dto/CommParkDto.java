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

    @NotNull
    private double x;
    @NotNull
    private double y;
    @NotNull
    private Integer parkTime;
}
