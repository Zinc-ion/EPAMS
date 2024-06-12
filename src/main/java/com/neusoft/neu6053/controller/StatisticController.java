package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.dao.viewObject.AQILevelListVO;
import com.neusoft.neu6053.dao.viewObject.AQILevelStatisticsVO;
import com.neusoft.neu6053.dao.viewObject.ProvinceGropConfListVO;
import com.neusoft.neu6053.dao.viewObject.ProvinceGropConfVO;
import com.neusoft.neu6053.services.ConfirmationService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stats")
@Tag(name = "StatisticControllerAPI", description = "统计数据相关接口")
public class StatisticController {
    private final ConfirmationService confirmationService;


    @Operation(
            summary = "AQI确认信息省分组分项查询接口",
            description = "根据省份分组AQI确认信息,返回各省各指数超标累计值，pageSize为-1时不分页"
    )
    @PostMapping("/selectProvinceGroupValue")
    public HttpResponseEntity selectProvinceGroupValue() {
        List<Confirmation> confirmations = confirmationService.getAllConfirmations(0,-1);
        List<ProvinceGropConfVO> provinceGropConfVOList = ProvinceGropConfListVO.getProvinceGropConfVOList();
        for(Confirmation c:confirmations){
            for (ProvinceGropConfVO p : provinceGropConfVOList) {
                if (c.getProvince().equals(p.getProvinceName())) {
                    if (c.getSo2() >= 50) {
                        p.setSo2(p.getSo2() + 1);
                    }
                    if (c.getCo() >= 4) {
                        p.setCo(p.getCo() + 1);
                    }
                    if (c.getPm25() >= 35) {
                        p.setPm25(p.getPm25() + 1);
                    }
                    if (Integer.parseInt(c.getPollutionLevel()) >= 3) {
                        p.setAqi(p.getAqi() + 1);
                    }
                }
            }
        }
        return HttpResponseEntity.success(provinceGropConfVOList);
    }

    @Operation(
            summary = "AQI空气质量指数分布查询接口",
            description = "返回各级别AQI空气质量指数分布情况"
    )
    @PostMapping("/selectAQILevelList")
    public HttpResponseEntity selectAQILevelList() {
        List<AQILevelStatisticsVO> aqiLevelStatisticsVOList = AQILevelListVO.getProvinceGropConfVOList();
        confirmationService.getAllConfirmations(0,-1).forEach(c -> {
            int level = Integer.parseInt(c.getPollutionLevel());
            aqiLevelStatisticsVOList.get(level - 1).setCount(aqiLevelStatisticsVOList.get(level - 1).getCount() + 1);
        });
        return HttpResponseEntity.success(aqiLevelStatisticsVOList);
    }




}
