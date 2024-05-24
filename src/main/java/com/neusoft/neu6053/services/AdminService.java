package com.neusoft.neu6053.services;

import com.neusoft.neu6053.dao.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【admins】的数据库操作Service
* @createDate 2024-05-24 13:51:30
*/
public interface AdminService extends IService<Admin> {
    List<Admin> loginAdmin(Admin admin);

}
