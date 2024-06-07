package com.neusoft.neu6053.config;
import com.neusoft.neu6053.config.interceptor.AdminAccessInterceptor;
import com.neusoft.neu6053.config.interceptor.InspectorAccessInterceptor;
import com.neusoft.neu6053.config.interceptor.LoginInterceptor;
import com.neusoft.neu6053.config.interceptor.SupervisorAccessInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 使用构造器注入，避免使用@Autowired注解
     * 使用@RequiredArgsConstructor注解，对所有final修饰的成员变量生成构造器
     */
    private final LoginInterceptor loginInterceptor;
    private final AdminAccessInterceptor adminAccessInterceptor;
    private final SupervisorAccessInterceptor supervisorAccessInterceptor;
    private final InspectorAccessInterceptor inspectorAccessInterceptor;


    private  final List<String> excludePaths = Arrays.asList(
            "/admins/login","/admins/add",
            "/supervisor/add","/supervisor/login","/supervisor/logout",
            "/inspector/add","/inspector/login","/inspector/logout",
            "/swagger-ui.html","/swagger-resources/**","/webjars/**","/v3/**","/doc.html",
            "/swagger-ui/**",
            "/favicon.ico","/error","/static/**"
    );


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把自定义的拦截器注册到全局拦截器中 排除登录和注册请求
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(excludePaths);

        registry.addInterceptor(adminAccessInterceptor)
                //需要有管理员身份才能使用的接口
                //拦截对管理员，监督员，网格员信息的接口访问,对info表的删除修改，对conf表的删除修改
                .addPathPatterns(
                        "/admins/**",
                        "/supervisor/**",
                        "/inspector/**",
                        "/info/delete/**","/info/modify/**",
                        "/conf/delete/**","/conf/modify/**")
                .excludePathPatterns(excludePaths);

        registry.addInterceptor(supervisorAccessInterceptor)
                //需要有监督员或管理员身份才能使用的接口
                //拦截对info表的添加
                .addPathPatterns(
                        "/info/add",
                        "/supervisor/logout")
                .excludePathPatterns(excludePaths);

        registry.addInterceptor(inspectorAccessInterceptor)
                //需要有网格员或管理员身份才能使用的接口
                //拦截对conf表的添加，与对info表的查询
                .addPathPatterns(
                        "/conf/add","/conf/select/**",
                        "/info/select/**",
                        "/inspector/logout")
                .excludePathPatterns(excludePaths);

    }
}
