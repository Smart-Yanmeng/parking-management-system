package com.qztc.parkingmanagementsystem.scache;

import com.qztc.parkingmanagementsystem.domain.dto.Point;

import java.util.List;

/**
 * @author congyijiu
 * @create 2024-05-06-11:24
 */
public class CommMap {

    private static List<Point> points;

    public static List<Point> getPoints() {
        if (points == null)
            throw new RuntimeException("points is null");
        return points;
    }

    public static void setPoints(List<Point> points) {
        CommMap.points = points;
    }
}
