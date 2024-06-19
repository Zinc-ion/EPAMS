package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dto.entity.Provincialcapital;
import com.neusoft.neu6053.services.ProvincialcapitalService;
import com.neusoft.neu6053.dto.mapper.ProvincialcapitalMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【provincialCapital】的数据库操作Service实现
* @createDate 2024-06-14 11:05:49
*/
@Service
public class ProvincialcapitalServiceImpl extends ServiceImpl<ProvincialcapitalMapper, Provincialcapital>
    implements ProvincialcapitalService{

    @Override
    public List<Provincialcapital> listAll() {
        return baseMapper.selectList(null);
    }

}




