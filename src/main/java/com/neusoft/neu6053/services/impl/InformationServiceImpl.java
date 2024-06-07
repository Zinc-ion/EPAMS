package com.neusoft.neu6053.services.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.dao.entity.Information;
import com.neusoft.neu6053.services.InformationService;
import com.neusoft.neu6053.dao.mapper.InformationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 1185911254@qq.com
* @description 针对表【information】的数据库操作Service实现
* @createDate 2024-06-06 15:57:11
*/
@RequiredArgsConstructor
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information>
    implements InformationService{

    private final InformationMapper informationMapper;

    @Override
    public int addInformation(Information information) {
        int flag;
        try {
            flag =  informationMapper.insert(information);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    @Override
    public int deleteInformation(Information information) {
        int flag;
        try {
            flag =  informationMapper.deleteById(information);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    @Override
    public int updateInformation(Information information) {
        int flag;
        try {
            flag =  informationMapper.updateById(information);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return flag;
    }

    @Override
    public Information getInformationById(Information information) {
        return informationMapper.selectById(information);
    }

    @Override
    public List<Information> getAllInformations(Integer curPage, Integer pageSize) {
        Page<Information> page = new Page<>(curPage, pageSize);
        return informationMapper.selectPage(page, null).getRecords();
}

}




