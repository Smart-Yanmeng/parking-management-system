package com.qztc.parkingmanagementsystem.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qztc.parkingmanagementsystem.util.JwtUtil;
import com.qztc.parkingmanagementsystem.vo.ResultVo;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();

        if (servletPath.equals("/parking/user/login") || servletPath.equals("/parking/user/register")) {
            filterChain.doFilter(servletRequest, servletResponse);

            return;
        }

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            servletResponse.setContentType("text/html;charset=utf-8");
            ResultVo resultVo = ResultVo.error("请重新登录");
            servletResponse.getWriter().write(new ObjectMapper().writeValueAsString(resultVo));

            return;
        }

        Long userId = JwtUtil.getUserId(token);

        System.out.println(userId);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
