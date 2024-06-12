package com.neusoft.neu6053.config;
import com.neusoft.neu6053.config.interceptor.AdminAccessInterceptor;
import com.neusoft.neu6053.config.interceptor.InspectorAccessInterceptor;
import com.neusoft.neu6053.config.interceptor.LoginInterceptor;
import com.neusoft.neu6053.config.interceptor.SupervisorAccessInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
            "/admins/login","/admins/add","/admins/logout",
            "/supervisor/add","/supervisor/login","/supervisor/logout",
            "/inspector/add","/inspector/login","/inspector/logout",
            "/swagger-ui.html","/swagger-resources/**","/webjars/**","/v3/**","/doc.html",
            "/swagger-ui/**",
            "/favicon.ico","/error","/static/**"
    );


    /**
     * 跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")// 设置允许跨域请求的域名
                .allowedHeaders("*")// 设置允许的请求头
                .allowCredentials(true)// 是否允许证书
                .allowedMethods("*")// 允许的方法
                .maxAge(3600);// 跨域允许时间
    }


    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把自定义的拦截器注册到全局拦截器中 排除登录和注册请求
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(excludePaths);

        registry.addInterceptor(adminAccessInterceptor)
                /**
                 * 只管理员身份才能使用的接口：
                 * 拦对管理员的所有操作的接口
                 * 监督员信息的删改查
                 * 网格员信息的删改查
                 * 对info表，conf表的删改
                 */
                .addPathPatterns(
                        "/admins/**",
                        "/supervisor/delete/**","/supervisor/modify","/supervisor/select/**",
                        "/inspector/delete/**","/inspector/modify","/inspector/select/**",
                        "/info/delete/**","/info/modify",
                        "/conf/delete/**","/conf/modify")
                .excludePathPatterns(
                        "/admins/login","/admins/add");

        registry.addInterceptor(supervisorAccessInterceptor)
                /**
                 * 需要有监督员或管理员身份才能使用的接口：
                 * 拦截对info表的添加
                 * 监督员退出登录
                 */
                .addPathPatterns(
                        "/info/add",
                        "/supervisor/logout");

        registry.addInterceptor(inspectorAccessInterceptor)
                /**
                 * 需要有网格员或管理员身份才能使用的接口：
                 * 拦截对conf表的添加查询
                 * 与对info表的查询
                 * 网格员退出登录
                 */
                .addPathPatterns(
                        "/conf/add","/conf/select/**",
                        "/info/select/**",
                        "/inspector/logout");

    }



}
