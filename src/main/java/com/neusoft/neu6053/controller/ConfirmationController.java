package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.dao.viewObject.ProvinceGropConfListVO;
import com.neusoft.neu6053.dao.viewObject.ProvinceGropConfVO;
import com.neusoft.neu6053.services.ConfirmationService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/conf")
@CrossOrigin(origins = "http://localhost:8081")
@Tag(name = "ConfirmationControllerAPI", description = "AQI确认信息相关接口")
public class ConfirmationController {
    private final ConfirmationService confirmationService;


    @Operation(
            summary = "AQI确认信息新增接口",
            description = "新增AQI确认信息，返回确认信息对象"
    )
    @PostMapping("/add")
    public HttpResponseEntity addConfirmation(@RequestBody Confirmation confirmation) {
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
            summary = "AQI确认信息省分组分项查询接口",
            description = "根据省份分组AQI确认信息,返回各省各指数超标累计值，pageSize为-1时不分页"
    )
    @PostMapping("/select/selectProvinceGroupValue")
    public HttpResponseEntity selectProvinceGroupValue() {
        List<Confirmation> confirmations = confirmationService.getAllConfirmations(0,-1);
        List<ProvinceGropConfVO> provinceGropConfVOList = ProvinceGropConfListVO.getProvinceGropConfVOList();
        for(Confirmation c:confirmations){
            for (ProvinceGropConfVO p : provinceGropConfVOList) {
                if (c.getProvince().equals(p.getProvinceName())) {
                    if (c.getSo2() >= 50) {
                        p.setSo2(p.getSo2() + 1);
                    }
                    if (c.getCo() >= 4) {
                        p.setCo(p.getCo() + 1);
                    }
                    if (c.getPm25() >= 35) {
                        p.setPm25(p.getPm25() + 1);
                    }
                    if (Integer.parseInt(c.getPollutionLevel()) >= 3) {
                        p.setAqi(p.getAqi() + 1);
                    }
                }
            }
        }
        return HttpResponseEntity.success(provinceGropConfVOList);
    }






}
