package com.neusoft.neu6053;

import org.junit.jupiter.api.Test;
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
    @Test public void testConnection() throws Exception {   System.out.println(this.dataSource);}

    @Test
    void contextLoads() {
    }




}
