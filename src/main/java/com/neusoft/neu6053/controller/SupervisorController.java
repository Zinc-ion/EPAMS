package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.services.SupervisorService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supervisor")
@Tag(name = "SupervisorControllerAPI", description = "监督员相关接口")
public class SupervisorController {
    @Autowired
    SupervisorService supervisorService;

    @PostMapping("/save")
    public HttpResponseEntity addSupervisor(@RequestBody Supervisor supervisor) {
        if (supervisorService.addSupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("Failed to add supervisor");
        }
    }

    @PostMapping("/delete")
    public HttpResponseEntity deleteSupervisor(@RequestBody Supervisor supervisor) {
        if (supervisorService.deleteSupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("Failed to delete supervisor");
        }
    }

    @PostMapping("/deleteById")
    public HttpResponseEntity deleteSupervisorById(@RequestBody String[] telId) {
        if (supervisorService.deleteSupervisorById(telId) ) {
            return HttpResponseEntity.success("success to delete supervisor");
        } else {
            return HttpResponseEntity.failure("Failed to delete supervisor");
        }
    }

    @PostMapping("/modify")
    public HttpResponseEntity modifySupervisor(@RequestBody Supervisor supervisor) {
        if (supervisorService.modifySupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("Failed to modify supervisor");
        }
    }

    @PostMapping("/selectAll")
    public HttpResponseEntity selectAllSupervisor(@RequestParam Integer curPage, @RequestParam Integer pageSize) {
        return HttpResponseEntity.success(supervisorService.selectAllSupervisor(curPage, pageSize));
    }

    @PostMapping("/selectByTel")
    public HttpResponseEntity selectSupervisorByTel(@RequestBody Supervisor supervisor) {
        return HttpResponseEntity.success(supervisorService.selectSupervisorByTel(supervisor));
    }

    @PostMapping("/selectByName")
    public HttpResponseEntity selectSupervisorByName(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Supervisor supervisor) {
        return HttpResponseEntity.success(supervisorService.selectSupervisorByName(curPage, pageSize, supervisor));
    }

    //    多条件查询，条件包括性别、手机号、姓名（模糊查询）
    @PostMapping("/selectByParams")
    public HttpResponseEntity selectSupervisorByParams(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Supervisor supervisor) {
        return HttpResponseEntity.success(supervisorService.selectSupervisorByParams(curPage, pageSize, supervisor));
    }

}
