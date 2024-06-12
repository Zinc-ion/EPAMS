package com.neusoft.neu6053;

import com.neusoft.neu6053.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootTest
@WebAppConfiguration
class Neu6053ApplicationTests {

    @Resource
    private DataSource dataSource;
    @Autowired
    private RedisUtils redisUtils;

    @Test public void testConnection() throws Exception {   System.out.println(this.dataSource);}

    @Test
    void contextLoads() {
    }

    @Test
    void RedisUtilsTest() {
        redisUtils.set("test", "test");
        redisUtils.set("test1", "test1");
        redisUtils.set("1test", "test2");
        redisUtils.set("1test1", "test3");
        System.out.print("1:" + redisUtils.getKeysSet("test"));
        System.out.print("2:" +redisUtils.getKeysSet("test*"));
        System.out.print("3:" +redisUtils.getKeysSet("*test"));
        System.out.print("4:" +redisUtils.getKeysSet("*test*"));
        redisUtils.deleteKeys("*test*");

    }




}
