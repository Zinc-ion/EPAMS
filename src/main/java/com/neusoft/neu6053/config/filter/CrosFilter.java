package com.neusoft.neu6053.config.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域配置
 */
public class CrosFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        //*号表示对所有请求都允许跨域访问
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        /**
         * Access-Control-Allow-Credentials 响应标头告诉浏览器服务器是否允许 HTTP 跨源请求携带凭据。
         * 凭据包括 cookie、TLS 客户端证书，或包含用户名和密码的认证标头。
         */
        response.setHeader("Access-Control-Max-Age", "3600");
        //用于判断request来自ajax还是传统请求
        response.setHeader("Access-Control-Allow-Headers", "*");

        /**
         * 由于浏览器将CORS请求分为两类：简单请求（simple request）和非简单请求（not-simple-request）。
         * 非简单请求 会在正式通信之前，增加一次HTTP请求，称之为预检请求。浏览器会先发起OPTIONS方法到服务器，以获知服务器是否允许该实际请求。
         */
        String method = request.getMethod();
        if(method.equalsIgnoreCase("OPTIONS")){
            servletResponse.getOutputStream().write("Success".getBytes("utf-8"));
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
