package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dao.entity.City;
import com.neusoft.neu6053.services.CityService;
import com.neusoft.neu6053.dao.mapper.CityMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【city】的数据库操作Service实现
* @createDate 2024-06-18 15:39:32
*/
@Service
@RequiredArgsConstructor
public class CityServiceImpl extends ServiceImpl<CityMapper, City>
    implements CityService{

    private final CityMapper cityMapper;

    @Override
    public List<City> selectCityByFatherId(Integer fatherId) {
        QueryWrapper<City> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fatherID", fatherId);
        return cityMapper.selectList(queryWrapper);
    }
}




