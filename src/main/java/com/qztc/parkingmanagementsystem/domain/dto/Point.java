package com.qztc.parkingmanagementsystem.domain.dto;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2024-04-15-16:43
 */
@Data
public class Point {
    public Integer id;
    public double x;
    public double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
