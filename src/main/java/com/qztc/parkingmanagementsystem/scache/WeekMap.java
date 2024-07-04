package com.qztc.parkingmanagementsystem.scache;

import java.util.HashMap;

/**
 * @author congyijiu
 * @create 2024-07-04-8:57
 */
public class WeekMap {

    private static final HashMap<Integer, String> weekMap = new HashMap<>();

    static {
        weekMap.put(0, "Mon");
        weekMap.put(1, "Tues");
        weekMap.put(2, "Wed");
        weekMap.put(3, "Thurs");
        weekMap.put(4, "Fri");
        weekMap.put(5, "Sat");
        weekMap.put(6, "Sun");
    }

    public static String getWeekStr(Integer week) {
        return weekMap.get(week);
    }

    private WeekMap() {

    }

    public static HashMap<Integer, String> getWeekMap() {
        return weekMap;
    }


}
