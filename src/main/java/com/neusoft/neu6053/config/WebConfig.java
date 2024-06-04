package com.neusoft.neu6053.config;
import com.neusoft.neu6053.config.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 使用构造器注入，避免使用@Autowired注解
     * 使用@RequiredArgsConstructor注解，对所有final修饰的成员变量生成构造器
     */
    private final LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把自定义的拦截器注册到全局拦截器中 排除登录和注册请求
        registry.addInterceptor(loginInterceptor).excludePathPatterns(
                "/admins/login","/admins/save",
                "/supervisor/save",
                "/swagger-ui.html","/swagger-resources/**","/webjars/**","/v3/**","/doc.html",
                "/swagger-ui/**",
                "/favicon.ico","/error","/static/**");

    }
}
