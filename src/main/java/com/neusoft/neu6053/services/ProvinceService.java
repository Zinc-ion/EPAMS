package com.neusoft.neu6053.services;

import com.neusoft.neu6053.dao.entity.Province;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【province】的数据库操作Service
* @createDate 2024-06-18 15:39:28
*/
public interface ProvinceService extends IService<Province> {
    List<Province> listAll();

}
