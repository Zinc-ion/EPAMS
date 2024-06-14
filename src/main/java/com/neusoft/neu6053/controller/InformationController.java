package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Information;
import com.neusoft.neu6053.services.InformationService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import com.neusoft.neu6053.utils.TimeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/info")
@Tag(name = "InformationAPI", description = "AQI反馈信息相关接口")
public class InformationController {
    private final InformationService informationService;

    @Operation(
            summary = "AQI反馈信息新增接口",
            description = "新增AQI反馈信息，返回反馈信息对象"
    )
    @PostMapping("/add")
    public HttpResponseEntity addInformation(@RequestBody Information information) {
        //设置日期时间与状态
        information.setDate(TimeUtil.getCurrentSqlDate());
        information.setTime(TimeUtil.getCurrentSqlTime());
        if (informationService.addInformation(information) == 1) {
            return HttpResponseEntity.success(information);
        } else {
            return HttpResponseEntity.failure("添加information失败");
        }
    }

    @Operation(
            summary = "AQI反馈信息删除接口",
            description = "根据information_id删除AQI反馈信息，返回删除的反馈信息对象"
    )
    @PostMapping("/delete/deleteById")
    public HttpResponseEntity deleteInformation(@RequestBody Information information) {
        if (informationService.deleteInformation(information) == 1) {
            return HttpResponseEntity.success(information);
        } else {
            return HttpResponseEntity.failure("删除失败，请检测information_id");
        }
    }

    @Operation(
            summary = "AQI反馈信息批量删除接口",
            description = "根据information_id数组批量删除AQI反馈信息，返回提示信息"
    )
    //批量删除
    @PostMapping("/delete/deleteByIdGroup")
    public HttpResponseEntity deleteInformationByIdGroup(@RequestBody int[] IdGroup) {
        if (informationService.deleteInformationByIdGroup(IdGroup) ) {
            return HttpResponseEntity.success("批量删除AQI反馈信息成功");
        } else {
            return HttpResponseEntity.failure("批量删除AQI反馈信息失败，请检查information_id");
        }
    }

    @Operation(
            summary = "AQI反馈信息修改接口",
            description = "根据information_id修改AQI反馈信息，返回修改后的反馈信息对象"
    )
    @PostMapping("/modify")
    public HttpResponseEntity modifyInformation(@RequestBody Information information) {
        if (informationService.updateInformation(information) == 1) {
            return HttpResponseEntity.success(information);
        } else {
            return HttpResponseEntity.failure("修改失败，请检测information_id");
        }
    }

    @Operation(
            summary = "AQI反馈信息委派接口",
            description = "根据传入的information_id与inspectorId修改对应AQI反馈信息内state为1，inspectId修改为指定的网格员id，返回修改后的反馈信息对象"
    )
    @PostMapping("/assign")
    public HttpResponseEntity assignInformation(@RequestBody Information information) {
        Information infoModified = informationService.getInformationById(information);
        //错误检测
        if(infoModified == null) {
            return HttpResponseEntity.failure("未找到表项，请检测information_id");
        } else if (infoModified.getState() != 0) {
            return HttpResponseEntity.failure("该信息已被委派或已完成，请检测information_id");
        }

        infoModified.setState(1);
        infoModified.setInspectorId(information.getInspectorId());
        if (informationService.updateInformation(infoModified) == 1) {
            return HttpResponseEntity.success(infoModified);
        } else {
            return HttpResponseEntity.failure("委派失败，请检测information_id");
        }
    }

    @Operation(
            summary = "AQI反馈信息查询接口",
            description = "分页查询AQI反馈信息，返回反馈信息列表，pageSize为-1时不分页"
    )
    @PostMapping("/select/selectAll")
    public HttpResponseEntity selectAllInformation(@RequestParam Integer curPage, @RequestParam Integer pageSize) {
        return HttpResponseEntity.success(informationService.getAllInformations(curPage, pageSize));
    }

    @Operation(
            summary = "AQI反馈信息查询接口2",
            description = "分页查询AQI反馈信息，返回反馈信息列表，pageSize为-1时不分页"
    )
    @PostMapping("/select/selectAll2")
    public HttpResponseEntity selectAllInformation2(@RequestParam Integer curPage, @RequestParam Integer pageSize) {
        return HttpResponseEntity.success(informationService.getAllInformations2(curPage, pageSize));
    }

    @Operation(
            summary = "AQI反馈信息根据Id查询接口",
            description = "根据information_id查询AQI反馈信息，返回反馈信息对象"
    )
    @PostMapping("/select/selectById")
    public HttpResponseEntity selectInformationById(@RequestBody Information information) {
        return HttpResponseEntity.success(informationService.getInformationById(information));
    }


    @Operation(
            summary = "AQI反馈信息多条件查询接口",
            description = "多条件查询，条件包括省(模糊)、市(模糊)、预估污染等级、反馈日期、是否指派，返回AQI反馈信息对象，pageSize为-1时不分页"
    )
    //    多条件查询，条件包括省(模糊)、市(模糊)、预估污染等级、反馈日期、是否指派
    @PostMapping("/select/selectByParams")
    public HttpResponseEntity selectInformationByParams(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Information information) {
        return HttpResponseEntity.success(informationService.selectInformationByParams(curPage, pageSize, information));
    }

    @Operation(
            summary = "根据inspector_id查询AQI反馈信息",
            description = "根据inspector_id查询AQI反馈信息，pageSize为-1时不分页"
    )
    @PostMapping("/select/selectByInsId")
    public HttpResponseEntity selectInformationByInsId(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Information information) {
        return HttpResponseEntity.success(informationService.getInformationByInsId(curPage, pageSize, information));
    }

    @Operation(
            summary = "根据supervisor_id查询AQI反馈信息",
            description = "根据supervisor_id查询AQI反馈信息，pageSize为-1时不分页"
    )
    @PostMapping("/select/selectBySupId")
    public HttpResponseEntity selectInformationBySupId(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Information information) {
        return HttpResponseEntity.success(informationService.getInformationBySupId(curPage, pageSize, information));
    }
}
