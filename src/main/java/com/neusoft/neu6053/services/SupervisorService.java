package com.neusoft.neu6053.services;

import com.neusoft.neu6053.dao.entity.Supervisor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author 1185911254@qq.com
* @description 针对表【supervisor】的数据库操作Service
* @createDate 2024-05-24 13:51:35
*/
public interface SupervisorService extends IService<Supervisor> {

    /**
     * 监督员登录
     * @param supervisor
     * @return Supervisor
     */
    Supervisor loginSupervisor(Supervisor supervisor);

    /**
     * 添加监督员信息
     * @param supervisor
     * @return int
     */
    int addSupervisor(Supervisor supervisor);

    /**
     * 删除监督员信息
     * @param supervisor
     * @return int
     */
    int deleteSupervisorById(Supervisor supervisor);

    /**
     * 根据id批量删除
     * @param telId
     * @return boolean
     */
    boolean deleteSupervisorByIdGroup(String[] telId);

    /**
     * 修改监督员信息
     * @param supervisor
     * @return int
     */
    int modifySupervisor(Supervisor supervisor);

    /**
     * 分页查询所有监督员信息
     * @param curPage
     * @param pageSize
     * @return List<Supervisor>
     */
    Map<String, Object> selectAllSupervisor(Integer curPage, Integer pageSize);

    /**
     * 根据手机号查询监督员信息
     * @param supervisor
     * @return List<Supervisor>
     */
    List<Supervisor> selectSupervisorByTel(Supervisor supervisor);


    /**
     * 多条件查询，条件包括性别、手机号、姓名（模糊查询）
     * @param curPage
     * @param pageSize
     * @param supervisor
     * @return List<Supervisor>
     */
    Map<String, Object> selectSupervisorByParams(Integer curPage, Integer pageSize, Supervisor supervisor);





}
