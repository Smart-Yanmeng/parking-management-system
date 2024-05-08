package com.qztc.parkingmanagementsystem.util;

import com.qztc.parkingmanagementsystem.domain.dto.Point;

import java.util.*;

public class MapUtil {
    private List<Point> points;
    static List<Point> generateRandomPoints(int numPoints, double minX, double maxX, double minY, double maxY) {
        List<Point> points = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numPoints; i++) {
            double x = minX + (maxX - minX) * random.nextDouble();
            double y = minY + (maxY - minY) * random.nextDouble();
            points.add(new Point(x, y));
        }

        return points;
    }


    // 计算两点之间的欧几里得距离
    static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    // 找到地图上距离给定点最近的几个点
    public static List<Point> findNearestPoints(List<Point> points,Point target, int k) throws RuntimeException {

        //校验入参
        if (points == null || points.isEmpty() || target == null || k <= 0) {
            throw new RuntimeException("小区地图数据为空或者目标点为空或者k值不合法");
        }

        PriorityQueue<Point> pq = new PriorityQueue<>(k, (p1, p2) -> {
            double dist1 = distance(p1, target);
            double dist2 = distance(p2, target);
            return Double.compare(dist2, dist1); // 大顶堆，按距离远近排列
        });
        for (Point point : points) {
            pq.offer(point);
            if (pq.size() > k) {
                pq.poll(); // 维护堆的大小为 k
            }
        }
        List<Point> nearestPoints = new ArrayList<>();
        while (!pq.isEmpty()) {
            nearestPoints.add(pq.poll());
        }
        Collections.reverse(nearestPoints); // 逆序，距离最近的点排在最前面
        return nearestPoints;
    }

    // 测试
    public static void main(String[] args) {
        Point target = new Point(50, 50);
        double start = System.currentTimeMillis();
        List<Point> points = generateRandomPoints(1000, 0, 100, 0, 100);
        List<Point> nearestPoints = findNearestPoints(points,target, 5);
        double end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start) + "ms");
        for (Point point : nearestPoints) {
            System.out.println("x: " + point.x + ", y: " + point.y);
        }
    }
}
