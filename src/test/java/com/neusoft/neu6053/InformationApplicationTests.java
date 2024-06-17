package com.neusoft.neu6053;

import com.neusoft.neu6053.dao.entity.Information;
import com.neusoft.neu6053.services.InformationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Rollback
public class InformationApplicationTests {
    @Autowired
    private InformationService informationService;

    @Test
    void contextLoads() {
    }

    @Test
    void testAddInformation() {
        Information information = new Information();
        information.setProvince("湖北省");
        information.setCity("武汉市");
        information.setCommunity("洪山区");
        information.setPollutionLevel("2");
        information.setDate(java.sql.Date.valueOf("2024-06-06"));
        information.setTime(java.sql.Time.valueOf("15:57:11"));
        information.setState(0);
        information.setSupervisorId("xj");
        information.setInspectorId("xj");
        information.setFeedback("无");
        information.setDeleted(0);
        assert informationService.addInformation(information) == 1;
    }

    @Test
    void testDeleteInformation() {
        Information information = new Information();
        information.setInformationId(3);
        assert informationService.deleteInformation(information) == 1;
    }

    @Test
    void testUpdateInformation() {
        Information information = new Information();
        information.setInformationId(3);
        information.setProvince("湖北省");
        information.setCity("武汉市");
        information.setCommunity("洪山区");
        information.setPollutionLevel("2");
        information.setDate(java.sql.Date.valueOf("2024-06-06"));
        information.setTime(java.sql.Time.valueOf("15:57:11"));
        information.setState(0);
        information.setSupervisorId("-2");
        information.setInspectorId("-2");
        information.setFeedback("无");
        information.setDeleted(0);
        assert informationService.updateInformation(information) == 1;
    }

    @Test
    void testGetInformationById() {
        Information information = new Information();
        information.setInformationId(3);
        assert informationService.getInformationById(information) != null;
    }

    @Test
    void testGetAllInformations() {
        assert informationService.getAllInformations(1, 10) != null;
    }


    @Test
    void testSelectInformationByParams() {
        Information information = new Information();
        information.setProvince("省");

        Information information1 = new Information();
        information1.setCity("市");

        Information information2 = new Information();
        information2.setPollutionLevel("2");
        information2.setDate(java.sql.Date.valueOf("2024-06-05"));
        information2.setState(0);

        Information information3 = new Information();
        information3.setProvince("湖北省");
        information3.setCity("武汉市");
        information3.setPollutionLevel("4");
        information3.setDate(java.sql.Date.valueOf("2024-06-05"));
        information3.setState(0);

        assert informationService.selectAQIFeedBackVOByParams(1, 10, information) != null;
        assert informationService.selectAQIFeedBackVOByParams(1, 10, information1) != null;
        assert informationService.selectAQIFeedBackVOByParams(1, 10, information2) != null;
        assert informationService.selectAQIFeedBackVOByParams(1, 10, information3) != null;
    }



}
