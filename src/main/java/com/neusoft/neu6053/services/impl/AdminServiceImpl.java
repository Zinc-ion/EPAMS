package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.services.AdminService;
import com.neusoft.neu6053.dao.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author 1185911254@qq.com
* @description 针对表【admins】的数据库操作Service实现
* @createDate 2024-05-24 13:51:30
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




