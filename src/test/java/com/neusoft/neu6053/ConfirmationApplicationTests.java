package com.neusoft.neu6053;

import com.neusoft.neu6053.dto.entity.Confirmation;
import com.neusoft.neu6053.services.ConfirmationService;
import com.neusoft.neu6053.utils.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@SpringBootTest
@Transactional
@Rollback
public class ConfirmationApplicationTests {
    @Autowired
    private ConfirmationService confirmationService;

    @Test
    void contextLoads() {
    }

    @Test
    void testTimeUtil() {
        System.out.println(TimeUtil.getCurrentSqlDate());
        System.out.println(TimeUtil.getCurrentSqlTime());
    }

    @Test
    public void testAddConfirmation() {
        Confirmation confirmation = new Confirmation();
        confirmation.setInformationId(-2);
        confirmation.setInspectorName("张三");
        confirmation.setSupervisorName("李四");
        confirmation.setProvince("海南省");
        confirmation.setCity("三亚市");
        confirmation.setCommunity("海棠区");
        confirmation.setPollutionLevel("2");
        confirmation.setSo2(0.1);
        confirmation.setCo(3.2);
        confirmation.setPm25(0.1);
        confirmation.setDate(TimeUtil.getCurrentSqlDate());
        confirmation.setTime(TimeUtil.getCurrentSqlTime());
        assert confirmationService.addConfirmation(confirmation) == 1;

    }

    @Test
    public void testDeleteConfirmation() {
        Confirmation confirmation = new Confirmation();
        confirmation.setConfId(1);
        assert confirmationService.deleteConfirmation(confirmation) == 1;
    }

    @Test
    public void testUpdateConfirmation() {
        Confirmation confirmation = new Confirmation();
        confirmation.setConfId(1);
        confirmation.setInformationId(-2);
        confirmation.setInspectorName("张三");
        confirmation.setSupervisorName("李四");
        confirmation.setProvince("辽宁");
        confirmation.setCity("沈阳市");
        confirmation.setCommunity("浑南区");
        confirmation.setPollutionLevel("2");
        confirmation.setSo2(0.1);
        confirmation.setCo(3.2);
        confirmation.setPm25(0.1);
        confirmation.setDate(java.sql.Date.valueOf("2024-06-06"));
        confirmation.setTime(java.sql.Time.valueOf("15:57:14"));
        assert confirmationService.updateConfirmation(confirmation) == 1;
    }

    @Test
    public void testGetConfirmationById() {
        Confirmation confirmation = new Confirmation();
        confirmation.setConfId(1);
        assert confirmationService.getConfirmationById(confirmation) != null;
    }

    @Test
    public void testGetAllConfirmations() {
        assert confirmationService.getAllConfirmations(1, 10) != null;
    }

    @Test
    public void testSelectConfirmationByParams() {

        Confirmation confirmation = new Confirmation();
        confirmation.setProvince("省");

        Confirmation confirmation1 = new Confirmation();
        confirmation1.setCity("市");

        Confirmation confirmation2 = new Confirmation();
        confirmation2.setDate(Date.valueOf("2024-06-05"));

        Confirmation confirmation3 = new Confirmation();
        confirmation3.setProvince("省");
        confirmation3.setCity("市");
        confirmation3.setDate(Date.valueOf("2024-06-05"));

        assert confirmationService.selectConfirmationByParams(1,10,confirmation) != null;
        assert confirmationService.selectConfirmationByParams(1,10,confirmation1) != null;
        assert confirmationService.selectConfirmationByParams(1,10,confirmation2) != null;
        assert confirmationService.selectConfirmationByParams(1,10,confirmation3) != null;
    }

}
