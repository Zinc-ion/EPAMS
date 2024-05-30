package com.neusoft.neu6053.config.interceptor;

import com.neusoft.neu6053.utils.RedisUtils;
import com.neusoft.neu6053.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private ThreadLocalUtil threadLocalUtil;

    //Controller方法处理之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
        try {
            //token+redis验证
            String token = request.getHeader("token");
            if (!redisUtils.hasKey(token)) {
                logger.info("token过期，请重新登录");
                //token失效 拦截请求
                throw new RuntimeException();
            } else {
                //刷新token有效期
                redisUtils.expire(token, 1, TimeUnit.HOURS);
                return true;
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