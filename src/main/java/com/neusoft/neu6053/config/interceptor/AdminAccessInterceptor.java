package com.neusoft.neu6053.config.interceptor;

import com.neusoft.neu6053.utils.RedisUtils;
import com.neusoft.neu6053.utils.RoleUtil;
import com.neusoft.neu6053.utils.ThreadLocalUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AdminAccessInterceptor implements HandlerInterceptor {
    //管理员Admin访问拦截器

    /**
     * 使用构造器注入，避免使用@Autowired注解
     * 使用@RequiredArgsConstructor注解，对所有final修饰的成员变量生成构造器
     */
    private final RedisUtils redisUtils;
    private final ThreadLocalUtil threadLocalUtil;

    //Controller方法处理之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
        try {
            //token+redis验证
            String token = request.getHeader("token");
            if (!redisUtils.hasKey(token)) {
                logger.info("管理员token过期，请重新登录");
                //token失效 拦截请求
                throw new RuntimeException();
            } else {
                //刷新token有效期
                redisUtils.expire(token, 1, TimeUnit.HOURS);
                if (((String)redisUtils.get(token)).startsWith(RoleUtil.ADMIN)) {
                    //如果value以ADMIN开头则放行访问所有以inspector开头的接口
                    return true;
                } else {
                    logger.info("管理员权限不足");
                    //抛异常拦截请求
                    throw new RuntimeException();
                }
            }

        } catch (Exception e) {
            //相应码401
            response.setStatus(401);
            //拦截请求
            return false;
        }


    }

    //该方法将在整个请求完成之后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空ThreadLocal中的信息 防止内存泄漏
        threadLocalUtil.remove();
    }

}
