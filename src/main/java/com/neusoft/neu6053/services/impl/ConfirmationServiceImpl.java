package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.dao.entity.Information;
import com.neusoft.neu6053.services.ConfirmationService;
import com.neusoft.neu6053.dao.mapper.ConfirmationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

}




