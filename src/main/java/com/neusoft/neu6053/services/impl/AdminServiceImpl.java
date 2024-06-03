package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.services.AdminService;
import com.neusoft.neu6053.dao.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【admins】的数据库操作Service实现
* @createDate 2024-05-24 13:51:30
*/
@RequiredArgsConstructor
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{
    /**
     * 使用构造器注入，避免使用@Autowired注解
     * 使用@RequiredArgsConstructor注解，对所有final修饰的成员变量生成构造器
     */
    private final AdminMapper adminMapper;

    @Override
    public List<Admin> loginAdmin(Admin admin) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_code", admin.getAdminCode());
        queryWrapper.eq("password", admin.getPassword());
        List<Admin> ifLogin = adminMapper.selectList(queryWrapper);
        return ifLogin;
    }
}




