package com.qztc.parkingmanagementsystem.util;

import com.qztc.parkingmanagementsystem.po.BUserPo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    public static final String SUBJECT = "task";

    public static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;

    public static final String SECRET = "login";

    public static final String TOKEN_PREFIX = "test";

    public static String genJsonWebToken(BUserPo user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) return null;

        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("username", user.getUsername())
                .claim("password", user.getPassword())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();

        token = TOKEN_PREFIX + token;

        return token;
    }

    public static Claims checkToken(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();

            return claims;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
