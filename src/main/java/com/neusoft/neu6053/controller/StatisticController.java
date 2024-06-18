package com.neusoft.neu6053.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.neu6053.dao.entity.City;
import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.dao.entity.Province;
import com.neusoft.neu6053.dao.entity.Provincialcapital;
import com.neusoft.neu6053.dao.viewObject.*;
import com.neusoft.neu6053.services.CityService;
import com.neusoft.neu6053.services.ConfirmationService;
import com.neusoft.neu6053.services.ProvinceService;
import com.neusoft.neu6053.services.ProvincialcapitalService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stats")
@Tag(name = "StatisticAPI", description = "统计数据相关接口")
public class StatisticController {
    private final ConfirmationService confirmationService;
    private final ProvincialcapitalService provincialcapitalService;
    private final ProvinceService provinceService;
    private final CityService cityService;


    @Operation(
            summary = "AQI确认信息省分组分项查询接口",
            description = "根据省份分组AQI确认信息,返回各省各指数超标累计值，pageSize为-1时不分页"
    )
    @PostMapping("/selectProvinceGroupValue")
    public HttpResponseEntity selectProvinceGroupValue() {
        Page<Confirmation> page = confirmationService.getAllConfirmations(0,-1);
        List<Confirmation> confirmations = page.getRecords();
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
        Page<Confirmation> page = confirmationService.getAllConfirmations(0,-1);
        page.getRecords().forEach(c -> {
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
        Page<Confirmation> page = confirmationService.getAllConfirmations(0, -1);
        List<Confirmation> allConfirmations = page.getRecords();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        int count = 0;
        for (Confirmation conf : allConfirmations) {
            if(aqiExceedTrendVOList.isEmpty()) {
                String date = sdf.format(conf.getDate());
                AQIExceedTrendVO aqiExceedTrendVO = new AQIExceedTrendVO();
                aqiExceedTrendVO.setYearAndMonth(date);
                aqiExceedTrendVO.setCount(++count);
                if (Integer.parseInt(conf.getPollutionLevel()) >= 3) {
                    aqiExceedTrendVO.setExceedCount(1);
                } else {
                    aqiExceedTrendVO.setExceedCount(0);
                }
                aqiExceedTrendVOList.add(aqiExceedTrendVO);
            } else {
                int flag = 0; //0表示没有找到对应的月份
                for (int i = 0; i < aqiExceedTrendVOList.size() ; i++ ) {
                    if (aqiExceedTrendVOList.get(i).getYearAndMonth().equals(sdf.format(conf.getDate()))) {
                        if (Integer.parseInt(conf.getPollutionLevel()) >= 3) {
                            aqiExceedTrendVOList.get(i).setExceedCount(aqiExceedTrendVOList.get(i).getExceedCount() + 1);
                        }
                        flag = 1;
                        break; //找到对应的月份，跳出循环
                    }
                }
                if (flag == 0) { //没有找到对应的月份 新增表项
                    AQIExceedTrendVO aqiExceedTrendVO = new AQIExceedTrendVO();
                    aqiExceedTrendVO.setYearAndMonth(sdf.format(conf.getDate()));
                    aqiExceedTrendVO.setCount(++count);
                    if (Integer.parseInt(conf.getPollutionLevel()) >= 3) {
                        aqiExceedTrendVO.setExceedCount(1);
                    } else {
                        aqiExceedTrendVO.setExceedCount(0);
                    }
                    aqiExceedTrendVOList.add(aqiExceedTrendVO);
                }
            }

        }

        return HttpResponseEntity.success(aqiExceedTrendVOList);
    }



    @Operation(
            summary = "AQI其他统计数据查询接口",
            description = "查询其他数据"
    )
    @PostMapping("/selectAQIElseData")
    public HttpResponseEntity selectAQIElseData() {
        Page<Confirmation> page = confirmationService.getAllConfirmations(0,-1);
        List<Confirmation> confirmations = page.getRecords();
        AQIElseDataVO aqiElseDataVO = new AQIElseDataVO();
        aqiElseDataVO.setTotal(confirmations.size());
        int goodAirQualityCount = 0;

        //AQI表中覆盖的城市
        Map<String, Object> coverCityMap = new HashMap<>();

        for (Confirmation c : confirmations) {
            if(coverCityMap.isEmpty()) {  //将覆盖城市名单加入coverCityList中
                coverCityMap.put(c.getCity(), 1);
            }else {
                if (!coverCityMap.containsKey(c.getCity())) { //去重添加有检测记录的城市
                    coverCityMap.put(c.getCity(), 1);
                }
            }
            //AQI等级小于三级则检测良好数量++
            if (Integer.parseInt(c.getPollutionLevel()) < 3) {
                goodAirQualityCount++;
            }
        }

        //设置检测良好数量
        aqiElseDataVO.setGoodAirQualityCount(goodAirQualityCount);

        List<Provincialcapital> provincialcapitals = provincialcapitalService.listAll();
        int coverCount = 0; //coverCity中覆盖省会城市的数量
        for (Provincialcapital p : provincialcapitals) {
            for (String c : coverCityMap.keySet()) {
                if (c.equals(p.getName())) {
                    coverCount++;
                }
            }
        }

        aqiElseDataVO.setProvincialCapitalCoverRate(String.format("%.2f",coverCount/(double)provincialcapitals.size() * 100) + "%");
        aqiElseDataVO.setMetropolisCoverRate(String.format("%.2f",coverCityMap.size()/105.0 * 100) + "%");
        return HttpResponseEntity.success(aqiElseDataVO);




    }


    @Operation(
            summary = "省份与其城市查询接口",
            description = "查询省份与其城市"
    )
    @PostMapping("/selectProvinceAndCity")
    public HttpResponseEntity selectProvinceAndCity() {
        List<ProvinceAndCityVO> provinceAndCityVOList = new ArrayList<>();
        List<Province> provinceList = provinceService.listAll();
        for (Province p : provinceList) {
            ProvinceAndCityVO provinceAndCityVO = new ProvinceAndCityVO();
            provinceAndCityVO.setProvinceName(p.getProvince());
            List<City> cityList = cityService.selectCityByFatherId(p.getProvinceid());
            List<String> cityName = new ArrayList<>();
            for (City c : cityList) {
                cityName.add(c.getCity());
            }
            provinceAndCityVO.setCityName(cityName);
            provinceAndCityVOList.add(provinceAndCityVO);
        }

        return HttpResponseEntity.success(provinceAndCityVOList);


    }






}
