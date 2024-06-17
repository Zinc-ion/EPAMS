package com.neusoft.neu6053.services;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.dao.entity.Information;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.dao.viewObject.AQIFeedBackVO;

import java.util.List;
import java.util.Map;

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
     * 根据id批量删除
     * @param informationIds
     * @return boolean
     */
    boolean deleteInformationByIdGroup(int[] informationIds);

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
     * 根据inspector_id获取被管理员分配的AQI反馈信息
     * @param information
     * @return Information
     */
    IPage<Information> getInformationByInsId(Integer curPage, Integer pageSize,Information information);

    /**
     * 根据supervisor_id获取监督员历史AQI反馈信息
     * @param information
     * @return Information
     */
    IPage<Information> getInformationBySupId(Integer curPage, Integer pageSize,Information information);



    /**
     * 分页获取所有AQI反馈信息
     * @param curPage
     * @param pageSize
     * @return List<Information>
     */
    IPage<Information> getAllInformations(Integer curPage, Integer pageSize);

    IPage<Information> getAllInformations2(Integer curPage, Integer pageSize);


    /**
     * 多条件查询，条件包括省(模糊)、市(模糊)、预估污染等级、反馈日期、是否指派
     * @param curPage
     * @param pageSize
     * @param information
     * @return List<Information>
     */
    Map<String, Object> selectAQIFeedBackVOByParams(Integer curPage, Integer pageSize, Information information);

    /**
     * 根据查询所有AQI反馈信息并包装为VO返回
     * @param curPage
     * @param pageSize
     * @return List<Information>
     */
    Map<String, Object> getAllAQIFeedBackVO(Integer curPage, Integer pageSize);

}
