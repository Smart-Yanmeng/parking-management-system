package com.qztc.parkingmanagementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author congyijiu
 * @create 2024-04-26-10:46
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(org.springframework.web.servlet.config.annotation.CorsRegistry registry) {
        registry.addMapping("/**")          // 允许跨域的路径
                .allowedOriginPatterns("*") // 允许跨域的域名，可以用*表示允许任何域名使用
                .allowCredentials(true)     // 允许使用cookie
                .allowedMethods("GET", "POST", "DELETE", "PUT") // 允许所有方法
                .allowedHeaders("*")        // 允许携带任何头信息
                .maxAge(3600);              // 跨域允许时间
    }
}