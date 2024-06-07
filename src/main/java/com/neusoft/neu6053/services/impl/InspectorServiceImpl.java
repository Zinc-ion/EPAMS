package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.dao.entity.Inspector;
import com.neusoft.neu6053.services.InspectorService;
import com.neusoft.neu6053.dao.mapper.InspectorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【inspector】的数据库操作Service实现
* @createDate 2024-06-06 15:57:19
*/
@Service
@RequiredArgsConstructor
public class InspectorServiceImpl extends ServiceImpl<InspectorMapper, Inspector>
    implements InspectorService{

    private final InspectorMapper inspectorMapper;


    @Override
    public Inspector loginInspector(Inspector inspector) {
        QueryWrapper<Inspector> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel_id", inspector.getTelId());
        queryWrapper.eq("password", inspector.getPassword());
        return inspectorMapper.selectOne(queryWrapper);
    }

    @Override
    public int saveInspector(Inspector inspector) {
        int flag;
        try {
            flag =  inspectorMapper.insert(inspector);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    @Override
    public int modifyInspector(Inspector inspector) {
        int flag;
        try {
            flag =  inspectorMapper.updateById(inspector);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    @Override
    public Inspector selectInspectorByTel(Inspector inspector) {
        return inspectorMapper.selectById(inspector);
    }

    @Override
    public List<Inspector> selectInspectorByProvinceAndCity(Inspector inspector) {
        QueryWrapper<Inspector> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("province", inspector.getProvince());
        queryWrapper.like("city", inspector.getCity());
        return inspectorMapper.selectList(queryWrapper);
    }

    @Override
    public int deleteInspectorByTel(Inspector inspector) {
        int flag;
        try {
            flag =  inspectorMapper.deleteById(inspector);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }
}




