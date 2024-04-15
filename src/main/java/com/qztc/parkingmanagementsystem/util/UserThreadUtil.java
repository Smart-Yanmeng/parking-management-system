package com.qztc.parkingmanagementsystem.util;

/**
 * @author congyijiu
 * @create 2024-04-01-11:32
 */
public class UserThreadUtil {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        threadLocal.set(userId);
    }

    public static Long getUserId() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
