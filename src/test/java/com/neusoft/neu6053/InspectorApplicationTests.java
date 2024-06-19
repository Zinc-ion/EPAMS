package com.neusoft.neu6053;

import com.neusoft.neu6053.dto.entity.Inspector;
import com.neusoft.neu6053.services.InspectorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
public class InspectorApplicationTests {
    @Autowired
    private InspectorService inspectorService;

    @Test
    void contextLoads() {
    }

    @Test
    void testSaveInspector() {
        Inspector inspector = new Inspector();
        inspector.setTelId("-1");
        inspector.setPassword("123456");
        inspector.setName("王五");
        inspector.setProvince("北京市");
        inspector.setCity("北京市");
        assert inspectorService.saveInspector(inspector) == 1;

    }

    @Test
    void testLoginInspector() {
        Inspector inspector = new Inspector();
        inspector.setTelId("123456");
        inspector.setPassword("123456");
        assert inspectorService.loginInspector(inspector) != null;
    }

    @Test
    void testModifyInspector() {
        Inspector inspector = new Inspector();
        inspector.setTelId("123456");
        inspector.setPassword("55555");
        inspector.setName("张三");
        assert inspectorService.modifyInspector(inspector) == 1;
    }

    @Test
    void testSelectInspectorByTel() {
        Inspector inspector = new Inspector();
        inspector.setTelId("123456");
        assert inspectorService.selectInspectorByTel(inspector) != null;
    }

    @Test
    void testSelectInspectorByProvinceAndCity() {
        Inspector inspector = new Inspector();
        inspector.setProvince("北京市");
        inspector.setCity("北京市");
        assert inspectorService.selectInspectorByProvinceAndCity(inspector) != null;
    }

    @Test
    void testDeleteInspectorByTel() {
        Inspector inspector = new Inspector();
        inspector.setTelId("123456");
        assert inspectorService.deleteInspectorByTel(inspector) == 1;
    }

}
