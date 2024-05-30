package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.utils.RedisUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
@Tag(name = "TestControllerAPI", description = "测试控制器接口")
public class TestController {

    @GetMapping("/testGet")
    public String testGet() {
        return "This is a test GET API.";
    }

    @PostMapping("/testPost")
    public String testPost(@RequestBody String body) {
        return "This is a test POST API. You posted: " + body;
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/testRedisSet")
    @ResponseBody
    public void testRedisSet() {
        //调用配置类保存key2
        redisTemplate.opsForValue().set("key2", "Hello Redis2");
        //调用工具类保存key3并设置过期时间30分钟
        redisUtils.set("key3", "Hello Redis3",30, TimeUnit.MINUTES);
        System.out.println("==========key2:" + redisUtils.get("key2") + " expireTime:" + redisUtils.getExpire("key2"));
        System.out.println("==========key3:" + redisUtils.get("key3") + " expireTime:" + redisUtils.getExpire("key3"));
    }

    @GetMapping("/testRedisDel")
    @ResponseBody
    public void testRedisDel() {
        System.out.println("==========key2:" + redisUtils.get("key2") + " expireTime:" + redisUtils.getExpire("key2"));
        System.out.println("==========key3:" + redisUtils.get("key3") + " expireTime:" + redisUtils.getExpire("key3"));
        redisUtils.del("key2");
        redisUtils.del("key3");
    }
}
