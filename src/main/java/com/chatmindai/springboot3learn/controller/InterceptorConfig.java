package com.chatmindai.springboot3learn.controller;

import com.chatmindai.springboot3learn.filter.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                //拦截
                .addPathPatterns("/api/user/**")
                //放行
                .excludePathPatterns("/api/user/login");
    }
}

