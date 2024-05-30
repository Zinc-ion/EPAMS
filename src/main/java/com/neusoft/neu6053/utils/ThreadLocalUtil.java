package com.neusoft.neu6053.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ThreadLocalUtil {
    // jdk建议将 ThreadLocal 定义为 private static ，这样就不会有弱引用，内存泄漏的问题了
    private static ThreadLocal<Map> mapThreadLocal = new ThreadLocal<>();

    //获取当前线程的存的变量
    public Map get() {
        return mapThreadLocal.get();
    }

    //设置当前线程的存的变量
    public void set(Map map) {
        this.mapThreadLocal.set(map);
    }

    //移除当前线程的存的变量
    public void remove() {
        this.mapThreadLocal.remove();
    }

}
