package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.dao.entity.Information;
import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.dao.mapper.SupervisorMapper;
import com.neusoft.neu6053.dao.viewObject.AQIFeedBackVO;
import com.neusoft.neu6053.services.InformationService;
import com.neusoft.neu6053.dao.mapper.InformationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 1185911254@qq.com
* @description 针对表【information】的数据库操作Service实现
* @createDate 2024-06-06 15:57:11
*/
@RequiredArgsConstructor
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information>
    implements InformationService{

    private final InformationMapper informationMapper;
    private final SupervisorMapper supervisorMapper;

    @Override
    public int addInformation(Information information) {
        int flag;
        try {
            flag =  informationMapper.insert(information);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    @Override
    public int deleteInformation(Information information) {
        int flag;
        try {
            flag =  informationMapper.deleteById(information);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    @Override
    public boolean deleteInformationByIdGroup(int[] informationIds) {
        int flag = 0;
        for (int id : informationIds) {
            if(1 != informationMapper.deleteById(id)) {
                flag++;
            }
        }
        return flag == 0;
    }

    @Override
    public int updateInformation(Information information) {
        int flag;
        try {
            flag =  informationMapper.updateById(information);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    @Override
    public Information getInformationById(Information information) {
        return informationMapper.selectById(information);
    }

    @Override
    public Map<String, Object> getInformationByInsId(Integer curPage, Integer pageSize,Information information) {
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("inspector_id", information.getInspectorId());
        queryWrapper.eq("state", 1); // 1表示已经委派但未完成 0为未委派 2为已完成
        Page<Information> page = new Page<>(curPage, pageSize);
        List<Information> records = informationMapper.selectPage(page, queryWrapper).getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("totalRecords", page.getTotal());
        map.put("totalPages", page.getPages());
        map.put("data", records);
        return map;
    }

    @Override
    public Map<String, Object> getInformationBySupId(Integer curPage, Integer pageSize,Information information) {
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supervisor_id", information.getSupervisorId());
        Page<Information> page = new Page<>(curPage, pageSize);
        Map<String, Object> map = new HashMap<>();
        List<Information> records = informationMapper.selectPage(page, queryWrapper).getRecords();
        map.put("totalRecords", page.getTotal());
        map.put("totalPages", page.getPages());
        map.put("data", records);
        return map;

    }

    @Override
    public Map<String, Object> getAllInformations(Integer curPage, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        Page<Information> page = new Page<>(curPage, pageSize);
        List<Information> records = informationMapper.selectPage(page, null).getRecords();
        map.put("totalRecords", page.getTotal());
        map.put("totalPages", page.getPages());
        map.put("data", records);
        return map;
    }

    @Override
    public Map<String, Object> selectInformationByParams(Integer curPage, Integer pageSize, Information information) {
        Page<Information> page = new Page<>(curPage, pageSize);
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        if(information.getProvince() != null) {
            queryWrapper.like("province", information.getProvince());
        }
        if(information.getCity() != null) {
            queryWrapper.like("city", information.getCity());
        }
        if(information.getPollutionLevel() != null) {
            queryWrapper.eq("pollution_level", information.getPollutionLevel());
        }
        if(information.getDate() != null) {
            //前端传入的是GMT格式时间，先转换为date类型再进行查询
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.like("date", sim.format(information.getDate()));
        }
        if(information.getState() != null) {
            queryWrapper.eq("state", information.getState());
        }
        List<Information> records = informationMapper.selectPage(page, queryWrapper).getRecords();

        if (records.isEmpty()) {
            return new HashMap<>();
        }

        //填充VO
        List<AQIFeedBackVO> aqiFeedBackVOS = new ArrayList<>();
        for (Information record : records) {
            AQIFeedBackVO aqiFeedBackVO = new AQIFeedBackVO();
            aqiFeedBackVO.setFeedbackId(record.getInformationId());

            Supervisor supervisor = supervisorMapper.selectById(record.getSupervisorId());
            if (supervisor == null) {
                return new HashMap<>();
            }

            aqiFeedBackVO.setSupName(supervisor.getRealName());
            aqiFeedBackVO.setSupSex(supervisor.getSex());
            aqiFeedBackVO.setBirthday(supervisor.getBirthday());
            aqiFeedBackVO.setSupTel(supervisor.getTelId());

            aqiFeedBackVO.setProvince(record.getProvince());
            aqiFeedBackVO.setCity(record.getCity());
            aqiFeedBackVO.setCommunity(record.getCommunity());
            aqiFeedBackVO.setFeedback(record.getFeedback());
            aqiFeedBackVO.setPollutionLevel(record.getPollutionLevel());
            aqiFeedBackVO.setDate(record.getDate());
            aqiFeedBackVO.setTime(record.getTime());

            aqiFeedBackVOS.add(aqiFeedBackVO);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("totalRecords", page.getTotal());
        map.put("totalPages", page.getPages());
        map.put("data", aqiFeedBackVOS);
        return map;
    }


}




