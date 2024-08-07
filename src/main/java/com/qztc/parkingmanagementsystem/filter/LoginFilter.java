package com.qztc.parkingmanagementsystem.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qztc.parkingmanagementsystem.domain.emuns.HttpCodeEnum;
import com.qztc.parkingmanagementsystem.util.JwtUtil;
import com.qztc.parkingmanagementsystem.domain.vo.ResultVo;
import com.qztc.parkingmanagementsystem.util.UserThreadUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter
@Slf4j
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String servletPath = request.getServletPath();


        if (servletPath.equals("/parking/user/login") || servletPath.equals("/parking/user/register")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //todo: 测试用，后续删除
        if (true){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            servletResponse.setContentType("text/html;charset=utf-8");
            ResultVo<?> resultVo = ResultVo.error(HttpCodeEnum.UNAUTHORIZED);
            servletResponse.getWriter().write(new ObjectMapper().writeValueAsString(resultVo));
            return;
        }
        Long userId = JwtUtil.getUserId(token);
        if (userId == null) {
            servletResponse.setContentType("text/html;charset=utf-8");
            ResultVo<?> resultVo = ResultVo.error(HttpCodeEnum.UNAUTHORIZED);
            servletResponse.getWriter().write(new ObjectMapper().writeValueAsString(resultVo));
            return;
        }

        UserThreadUtil.setUserId(userId);
        log.info("userId:{}", userId);
        filterChain.doFilter(servletRequest, servletResponse);
        UserThreadUtil.remove();
    }
}
