package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Information;
import com.neusoft.neu6053.services.InformationService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/info")
@Tag(name = "InformationControllerAPI", description = "AQI反馈信息相关接口")
public class InformationController {
    private final InformationService informationService;

    @PostMapping("/add")
    public HttpResponseEntity addInformation(@RequestBody Information information) {
        if (informationService.addInformation(information) == 1) {
            return HttpResponseEntity.success(information);
        } else {
            return HttpResponseEntity.failure("添加information失败");
        }
    }

    @PostMapping("/delete/deleteById")
    public HttpResponseEntity deleteInformation(@RequestBody Information information) {
        if (informationService.deleteInformation(information) == 1) {
            return HttpResponseEntity.success(information);
        } else {
            return HttpResponseEntity.failure("删除失败，请检测information_id");
        }
    }

//    //批量删除
//    @PostMapping("/deleteById")
//    public HttpResponseEntity deleteInformationById(@RequestBody String[] telId) {
//        if (informationService.deleteInformationById(telId) ) {
//            return HttpResponseEntity.success("success to delete information");
//        } else {
//            return HttpResponseEntity.failure("Failed to delete information, please check the tel_id");
//        }
//    }

    @PostMapping("/modify/modifyById")
    public HttpResponseEntity modifyInformation(@RequestBody Information information) {
        if (informationService.updateInformation(information) == 1) {
            return HttpResponseEntity.success(information);
        } else {
            return HttpResponseEntity.failure("修改失败，请检测information_id");
        }
    }

    @PostMapping("/select/selectAll")
    public HttpResponseEntity selectAllInformation(@RequestParam Integer curPage, @RequestParam Integer pageSize) {
        return HttpResponseEntity.success(informationService.getAllInformations(curPage, pageSize));
    }

    @PostMapping("/select/selectById")
    public HttpResponseEntity selectInformationById(@RequestBody Information information) {
        return HttpResponseEntity.success(informationService.getInformationById(information));
    }


    //    多条件查询，条件包括省(模糊)、市(模糊)、预估污染等级、反馈日期、是否指派
    @PostMapping("/select/selectByParams")
    public HttpResponseEntity selectInformationByParams(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Information information) {
        return HttpResponseEntity.success(informationService.selectInformationByParams(curPage, pageSize, information));
    }
}
