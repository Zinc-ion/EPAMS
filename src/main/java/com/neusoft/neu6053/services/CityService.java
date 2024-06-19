package com.neusoft.neu6053.services;

import com.neusoft.neu6053.dto.entity.City;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【city】的数据库操作Service
* @createDate 2024-06-18 15:39:32
*/
public interface CityService extends IService<City> {

    List<City> selectCityByFatherId(Integer fatherId);

}
