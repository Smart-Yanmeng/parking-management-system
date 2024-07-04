package com.qztc.parkingmanagementsystem.scache;

import com.qztc.parkingmanagementsystem.domain.po.Point;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author congyijiu
 * @create 2024-05-06-11:24
 */
public class CommMap {

    @Getter
    private static List<Point> points = new ArrayList<>();


    private CommMap() {

    }

    public static void setPoints(List<Point> points) {
        CommMap.points = points;
    }
}
