package com.neusoft.neu6053;

import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.dao.mapper.AdminMapper;
import com.neusoft.neu6053.dao.mapper.SupervisorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SupervisorApplicationTests {

    @Autowired
    private SupervisorMapper supervisorMapper;

    @Test
    void contextLoads() {
    }



    @Test
    public void testSelect() {
        List<Supervisor> list = supervisorMapper.selectList(null);
        for(Supervisor supervisor : list) {
            System.out.println(supervisor);
        }
    }

    //根据主键查询
    @Test
    public void testSelectById() {
        //注意：根据ID查询时要指定主键生成策略
        Supervisor supervisor = supervisorMapper.selectById(41);
        System.out.println(supervisor);
    }

    //插入
    @Test
    public void testInsert() {
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId("18842326593");
        supervisor.setPassword("123456");
        supervisor.setBirthday("10.16");
        supervisor.setSex(1);
        supervisor.setRemarks("");
        supervisor.setRealName("zhou");
        //注意：Insert时要指定主键生成策略
        int result = supervisorMapper.insert(supervisor);
        System.out.println(result);
        supervisorMapper.deleteById("18842326593");
    }

    //更新
    @Test
    public void testUpdate() {
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId("18842326593");
        supervisor.setPassword("123456");
        supervisor.setBirthday("10.16");
        supervisor.setSex(1);
        supervisor.setRemarks("");
        supervisor.setRealName("zhou");
        //注意：Insert时要指定主键生成策略
        supervisorMapper.insert(supervisor);
        supervisor.setSex(0);
        int result = supervisorMapper.updateById(supervisor);
        System.out.println(result);
        supervisorMapper.deleteById("18842326593");
    }

    //删除
    @Test
    public void testDelete() {
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId("18842326593");
        supervisor.setPassword("123456");
        supervisor.setBirthday("10.16");
        supervisor.setSex(1);
        supervisor.setRemarks("");
        supervisor.setRealName("zhou");
        supervisorMapper.insert(supervisor);
        //注意：Insert时要指定主键生成策略
        int result = supervisorMapper.deleteById("18842326593");
        System.out.println(result);
    }
}
