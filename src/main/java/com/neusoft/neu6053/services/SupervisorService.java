package com.neusoft.neu6053.services;

import com.neusoft.neu6053.dao.entity.Supervisor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【supervisor】的数据库操作Service
* @createDate 2024-05-24 13:51:35
*/
public interface SupervisorService extends IService<Supervisor> {

    int addSupervisor(Supervisor supervisor);

    int deleteSupervisor(Supervisor supervisor);

    int modifySupervisor(Supervisor supervisor);

//    分页查询所有监督员
    List<Supervisor> selectAllSupervisor(Integer curPage, Integer pageSize);

//    主键手机号精确查询
    List<Supervisor> selectSupervisorByTel(Supervisor supervisor);

//    姓名模糊查询
    List<Supervisor> selectSupervisorByName(Integer curPage, Integer pageSize,Supervisor supervisor);






}
