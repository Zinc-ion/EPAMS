package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dao.entity.Province;
import com.neusoft.neu6053.services.ProvinceService;
import com.neusoft.neu6053.dao.mapper.ProvinceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【province】的数据库操作Service实现
* @createDate 2024-06-18 15:39:28
*/
@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
    implements ProvinceService{

    private final ProvinceMapper provinceMapper;


    @Override
    public List<Province> listAll() {
        return provinceMapper.selectList(null);
    }
}




