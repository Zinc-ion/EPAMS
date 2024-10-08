package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dto.entity.Information;
import com.neusoft.neu6053.dto.entity.Supervisor;
import com.neusoft.neu6053.dto.mapper.SupervisorMapper;
import com.neusoft.neu6053.dto.viewObject.AQIFeedBackVO;
import com.neusoft.neu6053.services.InformationService;
import com.neusoft.neu6053.dto.mapper.InformationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public IPage<Information> getInformationByInsId(Integer curPage, Integer pageSize,Information information) {
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("inspector_id", information.getInspectorId());
        queryWrapper.eq("state", 1); // 1表示已经委派但未完成 0为未委派 2为已完成
        Page<Information> page = new Page<>(curPage, pageSize);
        page.setRecords(informationMapper.selectPage(page, queryWrapper).getRecords());
        return page;
    }

    @Override
    public IPage<Information> getInformationBySupId(Integer curPage, Integer pageSize,Information information) {
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("supervisor_id", information.getSupervisorId());
        Page<Information> page = new Page<>(curPage, pageSize);
        page.setRecords(informationMapper.selectPage(page, queryWrapper).getRecords());
       return page;

    }

    @Override
    public IPage<Information> getAllInformations(Integer curPage, Integer pageSize) {
        Page<Information> page = new Page<>(curPage, pageSize);
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("state",1); //只查询小于等于1的state
        page.setRecords(informationMapper.selectPage(page, queryWrapper).getRecords());
        return page;
    }



    @Override
    public Map<String, Object> getAllAQIFeedBackVO(Integer curPage, Integer pageSize) {
        Page<Information> page = new Page<>(curPage, pageSize);
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("state",1); //只查询小于等于1的state
        List<Information> records = informationMapper.selectPage(page, queryWrapper).getRecords();

        //填充VO
        List<AQIFeedBackVO> aqiFeedBackVOS = fillAQIFeedBackVO(records);

        //用VO返回，故使用不了IPage返回，向map中设置分页相关信息
        Map<String, Object> map = new HashMap<>();
        map.put("totalRecords", page.getTotal());
        map.put("totalPages", page.getPages());
        map.put("currentPage", page.getCurrent());
        map.put("pageSize", page.getSize());
        map.put("data", aqiFeedBackVOS);
        return map;
    }

    @Override
    public AQIFeedBackVO selectAQIFeedBackVOById(Integer informationId) {
        Information information = informationMapper.selectById(informationId);
        if (information == null) {
            return null;
        }
        List<Information> records = new ArrayList<>();
        records.add(information);
        List<AQIFeedBackVO> aqiFeedBackVOS = fillAQIFeedBackVO(records);
        return aqiFeedBackVOS.get(0);
    }


    @Override
    public Map<String, Object> selectAQIFeedBackVOByParams(Integer curPage, Integer pageSize, Information information) {
        Page<Information> page = new Page<>(curPage, pageSize);
        QueryWrapper<Information> queryWrapper = new QueryWrapper<>();
        if(information.getProvince() != null && !information.getProvince().isEmpty()) {
            queryWrapper.like("province", information.getProvince());
        }
        if(information.getCity() != null && !information.getCity().isEmpty()) {
            queryWrapper.like("city", information.getCity());
        }
        if(information.getPollutionLevel() != null && !information.getPollutionLevel().isEmpty()) {
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


        //填充VO
        List<AQIFeedBackVO> aqiFeedBackVOS = fillAQIFeedBackVO(records);

        //用VO返回，故使用不了IPage返回，向map中设置分页相关信息
        Map<String, Object> map = new HashMap<>();
        map.put("totalRecords", page.getTotal());
        map.put("totalPages", page.getPages());
        map.put("currentPage", page.getCurrent());
        map.put("pageSize", page.getSize());
        map.put("data", aqiFeedBackVOS);
        return map;
    }



    //填充VO
    private List<AQIFeedBackVO> fillAQIFeedBackVO(List<Information> records) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        List<AQIFeedBackVO> aqiFeedBackVOS = new ArrayList<>();
        //测空
        if (records.isEmpty()) return aqiFeedBackVOS;
        for (Information record : records) {
            AQIFeedBackVO aqiFeedBackVO = new AQIFeedBackVO();
            aqiFeedBackVO.setFeedbackId(record.getInformationId());
            Supervisor supervisor = supervisorMapper.selectById(record.getSupervisorId());
            if (supervisor == null) {                   //未找到id对应监督员则填充提示信息
                aqiFeedBackVO.setSupName("未找到监督员");
                aqiFeedBackVO.setSupSex(1);
                aqiFeedBackVO.setBirthday("未找到监督员");
                aqiFeedBackVO.setSupTel(record.getSupervisorId());
            } else {
                aqiFeedBackVO.setSupName(supervisor.getRealName());
                aqiFeedBackVO.setSupSex(supervisor.getSex());
                aqiFeedBackVO.setBirthday(supervisor.getBirthday());
                aqiFeedBackVO.setSupTel(supervisor.getTelId());
            }


            aqiFeedBackVO.setProvince(record.getProvince());
            aqiFeedBackVO.setCity(record.getCity());
            aqiFeedBackVO.setCommunity(record.getCommunity());
            aqiFeedBackVO.setFeedback(record.getFeedback());
            aqiFeedBackVO.setPollutionLevel(record.getPollutionLevel());

            //将日期与时间改成字符串标准形式传回前端
            aqiFeedBackVO.setDate(dateFormat.format(record.getDate()));
            aqiFeedBackVO.setTime(timeFormat.format(record.getTime()));

            aqiFeedBackVOS.add(aqiFeedBackVO);
        }
        return aqiFeedBackVOS;
    }


}




