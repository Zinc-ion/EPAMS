package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Confirmation;

import com.neusoft.neu6053.dao.entity.Information;
import com.neusoft.neu6053.dao.entity.Inspector;
import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.services.ConfirmationService;
import com.neusoft.neu6053.services.InformationService;
import com.neusoft.neu6053.services.InspectorService;
import com.neusoft.neu6053.services.SupervisorService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import com.neusoft.neu6053.utils.TimeUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/conf")
@Tag(name = "ConfirmationAPI", description = "AQI确认信息相关接口")
public class ConfirmationController {
    private final ConfirmationService confirmationService;
    private final InformationService informationService;
    private final SupervisorService supervisorService;
    private final InspectorService inspectorService;


    @Operation(
            summary = "AQI确认信息新增接口",
            description = "新增AQI确认信息，返回确认信息对象"
    )
    @PostMapping("/add")
    public HttpResponseEntity addConfirmation(@RequestBody Confirmation confirmation) {
        //设置时间
        confirmation.setDate(TimeUtil.getCurrentSqlDate());
        confirmation.setTime(TimeUtil.getCurrentSqlTime());

        //检查污染等级格式
        try {
            Integer.parseInt(confirmation.getPollutionLevel());
        } catch (NumberFormatException e) {
            return HttpResponseEntity.failure("污染等级格式错误,应该为字符串形式的罗马数字1~6");
        }

        Information information = informationService.getInformationById(new Information(confirmation.getInformationId()));
        //检查informationId是否存在
        if (information == null) {
            return HttpResponseEntity.failure("informationId对应info项不存在");
        }

        //设置name
        Supervisor supervisor = supervisorService.selectSupervisorByTel(new Supervisor(information.getSupervisorId())).get(0);
        Inspector inspector = inspectorService.selectInspectorByTel(new Inspector(information.getInspectorId()));
        if (supervisor == null || inspector == null) {
            return HttpResponseEntity.failure("supervisorId或inspectorId对应项不存在");
        }
        confirmation.setSupervisorName(supervisor.getRealName());
        confirmation.setInspectorName(inspector.getName());

        //添加数据
        if (confirmationService.addConfirmation(confirmation) == 1) {
            return HttpResponseEntity.success(confirmation);
        } else {
            return HttpResponseEntity.failure("添加confirmation失败");
        }
    }

    @Operation(
            summary = "AQI确认信息删除接口",
            description = "根据conf_id删除AQI确认信息，返回删除的确认信息对象"
    )
    @PostMapping("/delete/deleteById")
    public HttpResponseEntity deleteConfirmation(@RequestBody Confirmation confirmation) {
        if (confirmationService.deleteConfirmation(confirmation) == 1) {
            return HttpResponseEntity.success(confirmation);
        } else {
            return HttpResponseEntity.failure("删除失败，请检测conf_id");
        }
    }

    @Operation(
            summary = "AQI确认信息批量删除接口",
            description = "根据conf_id数组批量删除AQI确认信息，返回提示信息"
    )
    //批量删除
    @PostMapping("/delete/deleteByIdGroup")
    public HttpResponseEntity deleteConfirmationByIdGroup(@RequestBody int[] IdGroup) {
        if (confirmationService.deleteConfirmationByIdGroup(IdGroup) ) {
            return HttpResponseEntity.success("批量删除AQI确认信息成功");
        } else {
            return HttpResponseEntity.failure("批量删除AQI确认信息失败，请检查conf_id");
        }
    }

    @Operation(
            summary = "AQI确认信息修改接口",
            description = "根据tel_id修改AQI确认信息，返回修改后的确认信息对象"
    )
    @PostMapping("/modify")
    public HttpResponseEntity modifyConfirmation(@RequestBody Confirmation confirmation) {
        if (confirmationService.updateConfirmation(confirmation) == 1) {
            return HttpResponseEntity.success(confirmation);
        } else {
            return HttpResponseEntity.failure("Failed to modify confirmation, please check the tel_id");
        }
    }

    @Operation(
            summary = "AQI确认信息查询接口",
            description = "分页查询所有AQI确认信息，返回确认信息列表 pageSize为-1时不分页"
    )
    @PostMapping("/select/selectAll")
    public HttpResponseEntity selectAllConfirmation(@RequestParam Integer curPage, @RequestParam Integer pageSize) {
        return HttpResponseEntity.success(confirmationService.getAllConfirmations(curPage, pageSize));
    }

    @Operation(
            summary = "AQIConfirmVO查询接口",
            description = "分页查询所有AQIConfirmVO，返回AQIConfirmVO列表 pageSize为-1时不分页"
    )
    @PostMapping("/select/selectAllVO")
    public HttpResponseEntity selectAllAQIConfirmVO(@RequestParam Integer curPage, @RequestParam Integer pageSize) {
        return HttpResponseEntity.success(confirmationService.getAllAQIConfirmVO(curPage, pageSize));
    }



    @Operation(
            summary = "AQI确认信息根据Id查询接口",
            description = "根据conf_id查询AQI确认信息，返回确认信息对象"
    )
    @PostMapping("/select/selectById")
    public HttpResponseEntity selectConfirmationById(@RequestBody Confirmation confirmation) {
        return HttpResponseEntity.success(confirmationService.getConfirmationById(confirmation));
    }


    @Operation(
            summary = "AQI确认信息多条件查询接口",
            description = "根据省(模糊)、市（模糊）、确认日期查询AQI确认信息，返回确认信息列表，pageSize为-1时不分页"
    )
    //    多条件查询，条件包括省(模糊)、市（模糊）、确认日期
    @PostMapping("/select/selectByParams")
    public HttpResponseEntity selectConfirmationByParams(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Confirmation confirmation) {
        return HttpResponseEntity.success(confirmationService.selectConfirmationByParams(curPage, pageSize, confirmation));
    }


    @Operation(
            summary = "AQIConfirmVO多条件查询接口",
            description = "根据省(模糊)、市（模糊）、确认日期查询AQI确认信息，返回AQIConfirmVO列表，pageSize为-1时不分页"
    )
    //    多条件查询，条件包括省(模糊)、市（模糊）、确认日期
    @PostMapping("/select/selectVOByParams")
    public HttpResponseEntity selectAQIConfirmVOByParams(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Confirmation confirmation) {
        return HttpResponseEntity.success(confirmationService.selectAQIConfirmVOByParams(curPage, pageSize, confirmation));
    }








}
