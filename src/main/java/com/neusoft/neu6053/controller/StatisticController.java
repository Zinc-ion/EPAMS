package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.dao.viewObject.*;
import com.neusoft.neu6053.services.ConfirmationService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        Map<String, Object> map = confirmationService.getAllConfirmations(0,-1);
        List<Confirmation> confirmations = (List<Confirmation>) map.get("data");
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
        List<Confirmation> confirmations = (List<Confirmation>) confirmationService.getAllConfirmations(0,-1).get("data");
        confirmations.forEach(c -> {
            int level = Integer.parseInt(c.getPollutionLevel());
            aqiLevelStatisticsVOList.get(level - 1).setCount(aqiLevelStatisticsVOList.get(level - 1).getCount() + 1);
        });
        return HttpResponseEntity.success(aqiLevelStatisticsVOList);
    }


    @Operation(
            summary = "AQI空气质量指数超标趋势",
            description = "各月份AQI空气质量指数超标情况"
    )
    @PostMapping("/selectAQIExceedTrend")
    public HttpResponseEntity selectAQIExceedTrend() {
        List<AQIExceedTrendVO> aqiExceedTrendVOList = new ArrayList<>();
        Map<String ,Object> map = confirmationService.getAllConfirmations(0, -1);
        List<Confirmation> allConfirmations = (List<Confirmation>) map.get("data");
        int count = 0;
        int exceedCount = 0;
        for (Confirmation conf : allConfirmations) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String date = sdf.format(conf.getDate());
            AQIExceedTrendVO aqiExceedTrendVO = new AQIExceedTrendVO();
            try {
                aqiExceedTrendVO.setYearAndMonth(sdf.parse(date));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            aqiExceedTrendVO.setCount(++count);
            if (Integer.parseInt(conf.getPollutionLevel()) >= 3) {
                aqiExceedTrendVO.setExceedCount(++exceedCount);
            }
            aqiExceedTrendVOList.add(aqiExceedTrendVO);



        }


        return HttpResponseEntity.success(aqiExceedTrendVOList);
    }






}
