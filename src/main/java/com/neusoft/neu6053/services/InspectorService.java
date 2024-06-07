package com.neusoft.neu6053.services;

import com.neusoft.neu6053.dao.entity.Inspector;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【inspector】的数据库操作Service
* @createDate 2024-06-06 15:57:19
*/
public interface InspectorService extends IService<Inspector> {

    /**
     * 网格员登录
     * @param inspector
     * @return Inspector
     */
    Inspector loginInspector(Inspector inspector);

    /**
     * 添加网格员信息
     * @param inspector
     * @return int
     */
    int saveInspector(Inspector inspector);

    /**
     * 修改网格员信息
     * @param inspector
     * @return int
     */
    int modifyInspector(Inspector inspector);

    /**
     * 根据tel_id查询网格员信息
     * @param inspector
     * @return Inspector
     */
    Inspector selectInspectorByTel(Inspector inspector);

    /**
     * 根据省和市查询网格员信息
     * @param inspector
     * @return Inspector
     */
    List<Inspector> selectInspectorByProvinceAndCity(Inspector inspector);

    /**
     * 根据tel_id删除网格员信息
     * @param inspector
     * @return int
     */
    int deleteInspectorByTel(Inspector inspector);


}
