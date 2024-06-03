package com.neusoft.neu6053;

import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.dao.mapper.SupervisorMapper;
import com.neusoft.neu6053.services.SupervisorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SupervisorApplicationTests {
    /**
     * 测试类中不能使用@RequiredArgsConstructor注解
     */
    @Autowired
    private SupervisorMapper supervisorMapper;

    @Autowired
    private SupervisorService supervisorService;

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


    @Test
    public void testAddSupervisor() {
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId("111111");
        supervisor.setPassword("123456");
        supervisor.setBirthday("10.16");
        supervisor.setSex(1);
        supervisor.setRemarks("");
        supervisor.setRealName("zhou");
        int result = supervisorService.addSupervisor(supervisor);
        System.out.println(result);
        assert result == 1;
        supervisorService.deleteSupervisor(supervisor);
    }

    @Test
    public void testSelectByTel() {
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId("222222");
        supervisor.setPassword("123456");
        supervisor.setBirthday("10.16");
        supervisor.setSex(1);
        supervisor.setRemarks("");
        supervisor.setRealName("zhou");
        supervisorService.addSupervisor(supervisor);

        List<Supervisor> supervisors = supervisorService.selectSupervisorByTel(supervisor);
        assert supervisors.size() == 1;
        supervisorService.deleteSupervisor(supervisor);
    }

    @Test
    public void testSelectByName() {
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId("222222");
        supervisor.setPassword("123456");
        supervisor.setBirthday("10.16");
        supervisor.setSex(1);
        supervisor.setRemarks("");
        supervisor.setRealName("zhou");
        supervisorService.addSupervisor(supervisor);

        Supervisor supervisor2 = new Supervisor();
        supervisor2.setTelId("333333");
        supervisor2.setPassword("123456");
        supervisor2.setBirthday("10.16");
        supervisor2.setSex(1);
        supervisor2.setRemarks("");
        supervisor2.setRealName("zhou");
        supervisorService.addSupervisor(supervisor2);

        List<Supervisor> supervisors = supervisorService.selectSupervisorByName(1, 10, supervisor);
        for(Supervisor supervisor1 : supervisors) {
            System.out.println(supervisor1);
        }
        assert !supervisors.isEmpty();

        supervisorService.deleteSupervisor(supervisor);
        supervisorService.deleteSupervisor(supervisor2);
    }

    @Test
    public void testModifySupervisor() {
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId("111111");
        supervisor.setPassword("123456");
        supervisor.setBirthday("10.16");
        supervisor.setSex(1);
        supervisor.setRemarks("");
        supervisor.setRealName("zhou");
        supervisorService.addSupervisor(supervisor);
        System.out.println("修改前：" + supervisorService.selectSupervisorByTel(supervisor).get(0));

        Supervisor supervisor1 = new Supervisor();
        supervisor1.setTelId("111111");
        supervisor1.setPassword("123456");
        supervisor1.setBirthday("5.16");
        supervisor1.setSex(1);
        supervisor1.setRemarks("");
        supervisor1.setRealName("xie");
        supervisor1.setSex(0);
        int result = supervisorService.modifySupervisor(supervisor1);
        System.out.println("修改后：" + supervisorService.selectSupervisorByTel(supervisor1).get(0));
        assert result == 1;

        supervisorService.deleteSupervisor(supervisor1);
    }

    @Test
    public void testDeleteSupervisor() {
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId("111111");
        supervisor.setPassword("123456");
        supervisor.setBirthday("10.16");
        supervisor.setSex(1);
        supervisor.setRemarks("");
        supervisor.setRealName("zhou");
        supervisorService.addSupervisor(supervisor);

        int result = supervisorService.deleteSupervisor(supervisor);
        assert result == 1;
    }

    @Test
    public void testSelectByParams() {
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId("111111");
        supervisor.setPassword("123456");
        supervisor.setBirthday("10.16");
        supervisor.setSex(1);
        supervisor.setRemarks("");
        supervisor.setRealName("string");
        supervisorService.addSupervisor(supervisor);

        Supervisor supervisor1 = new Supervisor();
        supervisor1.setRealName("string");


        List<Supervisor> supervisors = supervisorService.selectSupervisorByParams(1, 10, supervisor1);
        assert !supervisors.isEmpty();
        supervisorService.deleteSupervisor(supervisor);
    }

    @Test
    public void testDeleteSupervisorById() {
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId("111111");
        supervisor.setPassword("123456");
        supervisor.setBirthday("10.16");
        supervisor.setSex(1);
        supervisor.setRemarks("");
        supervisor.setRealName("zhou");
        supervisorService.addSupervisor(supervisor);

        String[] telId = new String[1];
        telId[0] = "111111";
        boolean result = supervisorService.deleteSupervisorById(telId);
        assert result;
    }



}
