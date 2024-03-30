package com.qztc.parkingmanagementsystem.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    LoginFilter loginFilter() {
        return new LoginFilter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginFilter()).addPathPatterns("/**").excludePathPatterns("/login", "/register");

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
