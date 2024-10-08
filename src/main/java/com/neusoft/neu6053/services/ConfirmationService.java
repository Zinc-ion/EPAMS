package com.neusoft.neu6053.services;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.neu6053.dto.entity.Confirmation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.neu6053.dto.viewObject.AQIConfirmVO;

import java.util.Map;

/**
* @author 1185911254@qq.com
* @description 针对表【confirmation】的数据库操作Service
* @createDate 2024-06-06 15:57:14
*/
public interface ConfirmationService extends IService<Confirmation> {
    /**
     * 添加确认AQI信息
     * @param confirmation
     * @return int
     */
    int addConfirmation(Confirmation confirmation);

    /**
     * 删除确认AQI信息
     * @param confirmation
     * @return int
     */
    int deleteConfirmation(Confirmation confirmation);

    /**
     * 根据id批量删除
     * @param confIds
     * @return boolean
     */
    boolean deleteConfirmationByIdGroup(int[] confIds);


    /**
     * 更新确认AQI信息
     * @param confirmation
     * @return int
     */
    int updateConfirmation(Confirmation confirmation);

    /**
     * 根据confId获取确认AQI信息
     * @param confirmation
     * @return Confirmation
     */
    Confirmation getConfirmationById(Confirmation confirmation);

    /**
     * 分页获取所有确认AQI信息
     * @param curPage
     * @param pageSize
     * @return List<Confirmation>
     */
    Page<Confirmation> getAllConfirmations(Integer curPage, Integer pageSize);

    /**
     * 分页获取所有AQIConfirmVO
     * @param curPage
     * @param pageSize
     * @return List<Confirmation>
     */
    Map<String, Object> getAllAQIConfirmVO(Integer curPage, Integer pageSize);

    /**
     * 多条件查询，条件包括省(模糊)、市（模糊）、确认日期
     * @param curPage
     * @param pageSize
     * @param confirmation
     * @return List<Confirmation>
     */
    Page<Confirmation> selectConfirmationByParams(Integer curPage, Integer pageSize, Confirmation confirmation);

    /**
     * 多条件查询AQIConfirmVO，条件包括省(模糊)、市（模糊）、确认日期
     * @param curPage
     * @param pageSize
     * @param confirmation
     * @return List<Confirmation>
     */
    Map<String, Object> selectAQIConfirmVOByParams(Integer curPage, Integer pageSize, Confirmation confirmation);

    /**
     * 根据id查询AQIConfirmVO
     * @param confirmation
     * @return AQIConfirmVO
     */
    AQIConfirmVO getAQIConfirmVOById(Confirmation confirmation);

}
