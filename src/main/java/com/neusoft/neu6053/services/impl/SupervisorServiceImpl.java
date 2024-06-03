package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.services.SupervisorService;
import com.neusoft.neu6053.dao.mapper.SupervisorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【supervisor】的数据库操作Service实现
* @createDate 2024-05-24 13:51:35
*/
@RequiredArgsConstructor
@Service
public class SupervisorServiceImpl extends ServiceImpl<SupervisorMapper, Supervisor>
    implements SupervisorService{
    /**
     * 使用构造器注入，避免使用@Autowired注解
     * 使用@RequiredArgsConstructor注解，对所有final修饰的成员变量生成构造器
     */
    private final SupervisorMapper supervisorMapper;


    @Override
    public int addSupervisor(Supervisor supervisor) {
        return supervisorMapper.insert(supervisor);
    }

    @Override
    public int deleteSupervisor(Supervisor supervisor) {
        return supervisorMapper.deleteById(supervisor);
    }

    @Override
    public boolean deleteSupervisorById(String[] telId) {
        int flag = 0;
        for (String id : telId) {
            if(1 != supervisorMapper.deleteById(id)) {
                flag++;
            }
        }
        return flag == 0;
    }


    @Override
    public int modifySupervisor(Supervisor supervisor) {
        return supervisorMapper.updateById(supervisor);
    }

    @Override
    public List<Supervisor> selectAllSupervisor(Integer curPage, Integer pageSize) {
        QueryWrapper<Supervisor> queryWrapper = new QueryWrapper<>();
        Page<Supervisor> page = new Page<>(curPage, pageSize);
        return supervisorMapper.selectPage(page,queryWrapper).getRecords();
    }

    @Override
    public List<Supervisor> selectSupervisorByTel(Supervisor supervisor) {
        QueryWrapper<Supervisor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel_id", supervisor.getTelId());
        return supervisorMapper.selectList(queryWrapper);
    }

    @Override
    public List<Supervisor> selectSupervisorByName(Integer curPage, Integer pageSize, Supervisor supervisor) {
        QueryWrapper<Supervisor> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("real_name", supervisor.getRealName());
        Page<Supervisor> page = new Page<>(curPage, pageSize);
        return supervisorMapper.selectPage(page,queryWrapper).getRecords();
    }

    @Override
    public List<Supervisor> selectSupervisorByParams(Integer curPage, Integer pageSize, Supervisor supervisor) {
        QueryWrapper<Supervisor> queryWrapper = new QueryWrapper<>();
        if (supervisor.getSex() != null) {
            queryWrapper.eq("sex", supervisor.getSex());
        }
        if (supervisor.getTelId() != null) {
            queryWrapper.eq("tel_id", supervisor.getTelId());
        }
        if (supervisor.getRealName() != null) {
            queryWrapper.like("real_name", supervisor.getRealName());
        }
        Page<Supervisor> page = new Page<>(curPage, pageSize);
        return supervisorMapper.selectPage(page, queryWrapper).getRecords();
    }
}




