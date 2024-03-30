package com.qztc.parkingmanagementsystem.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class EncUtil {
    /**
     * MD5 加密
     *
     * @param data String
     * @return String
     */
    public static String MD5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : array) sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);

            return sb.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
