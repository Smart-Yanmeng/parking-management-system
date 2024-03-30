package com.qztc.parkingmanagementsystem.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qztc.parkingmanagementsystem.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;

public class LoginFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String accessToken = request.getHeader("token");

            if (accessToken == null) accessToken = request.getParameter("token");

            if (StringUtils.isNotBlank(accessToken)) {
                Claims claims = JwtUtil.checkToken(accessToken);

                if (claims == null) {
                    sendJsonMessage(response, "token失效，请重新登录");

                    return false;
                }

                String username = (String) claims.get("username");
                request.setAttribute("username", username);

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        sendJsonMessage(response, "token失效，请重新登录");

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    public static void sendJsonMessage(HttpServletResponse response, Object obj) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(obj));
            writer.close();
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
