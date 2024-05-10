package com.qztc.parkingmanagementsystem.scache;

import com.qztc.parkingmanagementsystem.domain.po.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author congyijiu
 * @create 2024-05-06-11:24
 */
public class CommMap {

    private static List<Point> points = new ArrayList<>();

    public static List<Point> getPoints() {
        return points;
    }

    public static void setPoints(List<Point> points) {
        CommMap.points = points;
    }
}
