package com.neusoft.neu6053.dto.viewObject;

import java.util.ArrayList;
import java.util.List;

public class AQILevelListVO {
    public static List<AQILevelStatisticsVO> getProvinceGropConfVOList() {
        List<AQILevelStatisticsVO> aqiLevelListVOList = new ArrayList<>();
        aqiLevelListVOList.add(new AQILevelStatisticsVO("1", "优", 0));
        aqiLevelListVOList.add(new AQILevelStatisticsVO("2", "良", 0));
        aqiLevelListVOList.add(new AQILevelStatisticsVO("3", "轻度污染", 0));
        aqiLevelListVOList.add(new AQILevelStatisticsVO("4", "中度污染", 0));
        aqiLevelListVOList.add(new AQILevelStatisticsVO("5", "重度污染", 0));
        aqiLevelListVOList.add(new AQILevelStatisticsVO("6", "严重污染", 0));
        return aqiLevelListVOList;
    }

}
