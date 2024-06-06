package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.services.ConfirmationService;
import com.neusoft.neu6053.dao.mapper.ConfirmationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 分页获取所有确认AQI信息
     * @param curPage
     * @param pageSize
     * @return List<Confirmation>
     */
    public List<Confirmation> getAllConfirmations(Integer curPage, Integer pageSize) {
        Page<Confirmation> page = new Page<>(curPage, pageSize);
        return confirmationMapper.selectPage(page, null).getRecords();
    }

}




