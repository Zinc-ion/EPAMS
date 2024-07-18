package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dto.entity.Confirmation;
import com.neusoft.neu6053.dto.entity.Information;
import com.neusoft.neu6053.dto.mapper.InformationMapper;
import com.neusoft.neu6053.dto.mapper.InspectorMapper;
import com.neusoft.neu6053.dto.mapper.SupervisorMapper;
import com.neusoft.neu6053.dto.viewObject.AQIConfirmVO;
import com.neusoft.neu6053.services.ConfirmationService;
import com.neusoft.neu6053.dto.mapper.ConfirmationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 1185911254@qq.com
* @description 针对表【confirmation】的数据库操作Service实现
* @createDate 2024-06-06 15:57:14
*/
@RequiredArgsConstructor
@Service
public class ConfirmationServiceImpl extends ServiceImpl<ConfirmationMapper, Confirmation>
    implements ConfirmationService{

    private final ConfirmationMapper confirmationMapper;
    private final SupervisorMapper supervisorMapper;
    private final InspectorMapper inspectorMapper;
    private final InformationMapper informationMapper;

    /**
     * 添加确认AQI信息
     * @param confirmation
     * @return int
     */
    public int addConfirmation(Confirmation confirmation) {
        int flag;
        try {
            flag =  confirmationMapper.insert(confirmation);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    /**
     * 删除确认AQI信息
     * @param confirmation
     * @return int
     */
    public int deleteConfirmation(Confirmation confirmation) {
        int flag;
        try {
            flag =  confirmationMapper.deleteById(confirmation);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    @Override
    public boolean deleteConfirmationByIdGroup(int[] confIds) {
        int flag = 0;
        for (int id : confIds) {
            if(1 != confirmationMapper.deleteById(id)) {
                flag++;
            }
        }
        return flag == 0;
    }


    /**
     * 更新确认AQI信息
     * @param confirmation
     * @return int
     */
    public int updateConfirmation(Confirmation confirmation) {
        int flag;
        try {
            flag =  confirmationMapper.updateById(confirmation);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    /**
     * 根据confId获取确认AQI信息
     * @param confirmation
     * @return Confirmation
     */
    public Confirmation getConfirmationById(Confirmation confirmation) {
        return confirmationMapper.selectById(confirmation);
    }

    /**
     * 分页获取所有确认AQI信息 pageSize为-1时不分页
     * @param curPage
     * @param pageSize
     * @return List<Confirmation>
     */
    public Page<Confirmation> getAllConfirmations(Integer curPage, Integer pageSize) {
        Page<Confirmation> page = new Page<>(curPage, pageSize);
        page.setRecords(confirmationMapper.selectPage(page, null).getRecords());
        return page;
    }

    @Override
    public Map<String, Object> getAllAQIConfirmVO(Integer curPage, Integer pageSize) {
        Page<Confirmation> page = new Page<>(curPage, pageSize);
        List<AQIConfirmVO> aqiConfirmVOList = fillAQIConfirmVO(confirmationMapper.selectPage(page, null).getRecords());

        //用VO返回，故使用不了IPage返回，向map中设置分页相关信息
        Map<String, Object> map = new HashMap<>();
        map.put("totalRecords", page.getTotal());
        map.put("totalPages", page.getPages());
        map.put("currentPage", page.getCurrent());
        map.put("pageSize", page.getSize());
        map.put("data", aqiConfirmVOList);
        return map;
    }

    /**
     * 多条件查询，条件包括省、市、确认日期
     * @param curPage
     * @param pageSize
     * @param confirmation
     * @return List<Confirmation>
     */
    @Override
    public Page<Confirmation> selectConfirmationByParams(Integer curPage, Integer pageSize, Confirmation confirmation) {
        Page<Confirmation> page = new Page<>(curPage, pageSize);
        QueryWrapper<Confirmation> queryWrapper = new QueryWrapper<>();
        if(confirmation.getProvince() != null && !confirmation.getProvince().isEmpty()) {
            queryWrapper.like("province", confirmation.getProvince());
        }
        if(confirmation.getCity() != null && !confirmation.getCity().isEmpty()) {
            queryWrapper.like("city", confirmation.getCity());
        }
        if(confirmation.getDate() != null) {
            //前端传入的是GMT格式时间，先转换为date类型再进行查询
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.like("date", sim.format(confirmation.getDate()));
        }
        page.setRecords(confirmationMapper.selectPage(page, queryWrapper).getRecords());

        return page;


    }

    @Override
    public Map<String, Object> selectAQIConfirmVOByParams(Integer curPage, Integer pageSize, Confirmation confirmation) {
        Page<Confirmation> page = new Page<>(curPage, pageSize);
        QueryWrapper<Confirmation> queryWrapper = new QueryWrapper<>();
        if(confirmation.getProvince() != null && !confirmation.getProvince().isEmpty()) {
            queryWrapper.like("province", confirmation.getProvince());
        }
        if(confirmation.getCity() != null && !confirmation.getCity().isEmpty()) {
            queryWrapper.like("city", confirmation.getCity());
        }
        if(confirmation.getDate() != null) {
            //前端传入的是GMT格式时间，先转换为date类型再进行查询
            SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
            queryWrapper.like("date", sim.format(confirmation.getDate()));
        }
        List<AQIConfirmVO> aqiConfirmVOList = fillAQIConfirmVO(confirmationMapper.selectPage(page, queryWrapper).getRecords());


        //用VO返回，故使用不了IPage返回，向map中设置分页相关信息
        Map<String, Object> map = new HashMap<>();
        map.put("totalRecords", page.getTotal());
        map.put("totalPages", page.getPages());
        map.put("currentPage", page.getCurrent());
        map.put("pageSize", page.getSize());
        map.put("data", aqiConfirmVOList);
        return map;
    }

    @Override
    public AQIConfirmVO getAQIConfirmVOById(Confirmation confirmation) {
        Confirmation record = confirmationMapper.selectById(confirmation);
        List<Confirmation> recordList = new ArrayList<>();
        recordList.add(record);
        List<AQIConfirmVO> aqiConfirmVOList = fillAQIConfirmVO(recordList);
        return aqiConfirmVOList.get(0);
    }


    //填充VO
    private List<AQIConfirmVO> fillAQIConfirmVO(List<Confirmation> records) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        List<AQIConfirmVO> aqiConfirmVOList = new ArrayList<>();
        //测空
        if (records.isEmpty()) return aqiConfirmVOList;

        //包装成VO
        for (Confirmation record : records) {
            AQIConfirmVO aqiConfirmVO = new AQIConfirmVO();
            Information information = informationMapper.selectById(record.getInformationId());
            if (information == null) {
                aqiConfirmVO.setInsTel("未找到info");
                aqiConfirmVO.setSupTel("未找到info");
                aqiConfirmVO.setFeedback("未找到info");
            } else {
                aqiConfirmVO.setInsTel(information.getInspectorId());
                aqiConfirmVO.setSupTel(information.getSupervisorId());
                aqiConfirmVO.setFeedback(information.getFeedback());
            }

            aqiConfirmVO.setConfirmId(record.getConfId());
            aqiConfirmVO.setInfoId(record.getInformationId());
            aqiConfirmVO.setProvince(record.getProvince());
            aqiConfirmVO.setCity(record.getCity());
            aqiConfirmVO.setCommunity(record.getCommunity());
            aqiConfirmVO.setPollutionLevel(record.getPollutionLevel());
            //将日期与时间改成字符串标准形式传回前端
            aqiConfirmVO.setDate(dateFormat.format(record.getDate()));
            aqiConfirmVO.setTime(timeFormat.format(record.getTime()));

            //因Confirmation中未存网格员与监督员id和feedback故取出对应info，从info内取两者id和feedback
            aqiConfirmVO.setInsName(record.getInspectorName());

            aqiConfirmVO.setSupName(record.getSupervisorName());


            aqiConfirmVOList.add(aqiConfirmVO);
        }
        return aqiConfirmVOList;
    }




}




