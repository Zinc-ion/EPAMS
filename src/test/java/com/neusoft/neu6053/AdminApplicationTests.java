package com.neusoft.neu6053;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.dao.mapper.AdminMapper;
import com.neusoft.neu6053.services.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AdminApplicationTests {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    void contextLoads() {
    }

    @Test
    void testLogin() {
        Admin admin = new Admin();
        admin.setAdminCode("111");
        admin.setPassword("111");
        Admin admin1 = adminService.loginAdmin(admin);
        assert admin1 != null;
        System.out.println("登录账户信息：" + admin1);
    }

    @Test
    void testPageSelect() {
//        测试分页查询
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        Page<Admin> page = new Page<>(1, 3);
        List<Admin> pageAdmins = adminMapper.selectPage(page,queryWrapper).getRecords();
        System.out.println(pageAdmins);
        assert pageAdmins.size() == 3;
    }


    @Test
     void testSelect() {
        List<Admin> list = adminMapper.selectList(null);
        for(Admin admin : list) {
            System.out.println(admin);
        }
    }
}
