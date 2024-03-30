package com.qztc.parkingmanagementsystem.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Calendar;
import java.util.Date;

public class JwtUtil {

    private final static String SECRET = "ParkingManagementSystem";

    public static String createToken(Long userId) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        String token = JWT.create().withExpiresAt(calendar.getTime())
                .withIssuedAt(new Date())
                .withClaim("userId", userId)
                .sign(Algorithm.HMAC256(SECRET));

        System.out.println(token);

        return token;
    }

    public static boolean verifyToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public static Long getUserId(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaim("username").asLong();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
