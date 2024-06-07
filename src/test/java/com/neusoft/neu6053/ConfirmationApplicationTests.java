package com.neusoft.neu6053;

import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.services.ConfirmationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ConfirmationApplicationTests {
    @Autowired
    private ConfirmationService confirmationService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testAddConfirmation() {
        Confirmation confirmation = new Confirmation();
        confirmation.setInformationId(-1);
        confirmation.setInspectorName("张三");
        confirmation.setSupervisorName("李四");
        confirmation.setProvince("北京市");
        confirmation.setCity("北京");
        confirmation.setCommunity("海淀区");
        confirmation.setPollutionLevel("轻度污染 二级");
        confirmation.setSo2(0.1);
        confirmation.setCo(3.2);
        confirmation.setPm25(0.1);
        confirmation.setData(java.sql.Date.valueOf("2024-06-06"));
        confirmation.setTime(java.sql.Time.valueOf("15:57:14"));

    }

}
