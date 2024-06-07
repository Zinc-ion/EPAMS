package com.neusoft.neu6053.services;

import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.dao.entity.Information;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【information】的数据库操作Service
* @createDate 2024-06-06 15:57:11
*/
public interface InformationService extends IService<Information> {
    /**
     * 添加AQI反馈信息
     * @param information
     * @return int
     */
    int addInformation(Information information);

    /**
     * 删除AQI反馈信息
     * @param information
     * @return int
     */
    int deleteInformation(Information information);

    /**
     * 更新AQI反馈信息
     * @param information
     * @return int
     */
    int updateInformation(Information information);

    /**
     * 根据id获取AQI反馈信息
     * @param information
     * @return Information
     */
    Information getInformationById(Information information);

    /**
     * 分页获取所有AQI反馈信息
     * @param curPage
     * @param pageSize
     * @return List<Information>
     */
    List<Information> getAllInformations(Integer curPage, Integer pageSize);

}
