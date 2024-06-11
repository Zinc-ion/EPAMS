package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Confirmation;
import com.neusoft.neu6053.services.ConfirmationService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/conf")
@Tag(name = "ConfirmationControllerAPI", description = "AQI确认信息相关接口")
public class ConfirmationController {
    private final ConfirmationService confirmationService;
    


    @PostMapping("/add")
    public HttpResponseEntity addConfirmation(@RequestBody Confirmation confirmation) {
        if (confirmationService.addConfirmation(confirmation) == 1) {
            return HttpResponseEntity.success(confirmation);
        } else {
            return HttpResponseEntity.failure("添加confirmation失败");
        }
    }

    @PostMapping("/delete/deleteById")
    public HttpResponseEntity deleteConfirmation(@RequestBody Confirmation confirmation) {
        if (confirmationService.deleteConfirmation(confirmation) == 1) {
            return HttpResponseEntity.success(confirmation);
        } else {
            return HttpResponseEntity.failure("删除失败，请检测conf_id");
        }
    }

    //批量删除
    @PostMapping("/delete/deleteByIdGroup")
    public HttpResponseEntity deleteConfirmationByIdGroup(@RequestBody int[] IdGroup) {
        if (confirmationService.deleteConfirmationByIdGroup(IdGroup) ) {
            return HttpResponseEntity.success("批量删除AQI确认信息成功");
        } else {
            return HttpResponseEntity.failure("批量删除AQI确认信息失败，请检查conf_id");
        }
    }

    @PostMapping("/modify")
    public HttpResponseEntity modifyConfirmation(@RequestBody Confirmation confirmation) {
        if (confirmationService.updateConfirmation(confirmation) == 1) {
            return HttpResponseEntity.success(confirmation);
        } else {
            return HttpResponseEntity.failure("Failed to modify confirmation, please check the tel_id");
        }
    }

    @PostMapping("/select/selectAll")
    public HttpResponseEntity selectAllConfirmation(@RequestParam Integer curPage, @RequestParam Integer pageSize) {
        return HttpResponseEntity.success(confirmationService.getAllConfirmations(curPage, pageSize));
    }

    @PostMapping("/select/selectById")
    public HttpResponseEntity selectConfirmationById(@RequestBody Confirmation confirmation) {
        return HttpResponseEntity.success(confirmationService.getConfirmationById(confirmation));
    }


    //    多条件查询，条件包括省(模糊)、市（模糊）、确认日期
    @PostMapping("/select/selectByParams")
    public HttpResponseEntity selectConfirmationByParams(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Confirmation confirmation) {
        return HttpResponseEntity.success(confirmationService.selectConfirmationByParams(curPage, pageSize, confirmation));
    }



}
