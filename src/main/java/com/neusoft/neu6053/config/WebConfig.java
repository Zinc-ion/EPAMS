package com.neusoft.neu6053.config;

import com.neusoft.neu6053.config.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把自定义的拦截器注册到全局拦截器中 排除登录和注册请求
        registry.addInterceptor(loginInterceptor).excludePathPatterns(
                "/admins/login","/admins/save",
                "/supervisor/save",
                "/swagger-ui.html","/swagger-resources/**","/webjars/**","/v3/**","/doc.html",
                "/swagger-ui/**");

    }
}
