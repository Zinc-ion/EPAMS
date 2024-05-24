package com.neusoft.neu6053;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.neusoft.neu6053.dao.mapper")
public class Neu6053Application {

    public static void main(String[] args) {
        SpringApplication.run(Neu6053Application.class, args);
    }

}
