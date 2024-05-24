package com.neusoft.neu6053;

import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.dao.mapper.AdminMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AdminApplicationTests {

    @Autowired
    private AdminMapper adminMapper;

    @Test
    void contextLoads() {
    }



    @Test
    public void testSelect() {
        List<Admin> list = adminMapper.selectList(null);
        for(Admin admin : list) {
            System.out.println(admin);
        }
    }
}
