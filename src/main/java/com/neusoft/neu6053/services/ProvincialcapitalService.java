package com.neusoft.neu6053.services;

import com.neusoft.neu6053.dto.entity.Provincialcapital;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【provincialCapital】的数据库操作Service
* @createDate 2024-06-14 11:05:49
*/
public interface ProvincialcapitalService extends IService<Provincialcapital> {
    List<Provincialcapital> listAll();

}
