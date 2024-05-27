package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.services.SupervisorService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/supervisor")
@Tag(name = "SupervisorControllerAPI", description = "监督员相关接口")
public class SupervisorController {
    @Autowired
    SupervisorService supervisorService;

    @PostMapping("/addSupervisor")
    public HttpResponseEntity addSupervisor(Supervisor supervisor) {
        if (supervisorService.addSupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("Failed to add supervisor");
        }
    }

    @PostMapping("/deleteSupervisor")
    public HttpResponseEntity deleteSupervisor(Supervisor supervisor) {
        if (supervisorService.deleteSupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("Failed to delete supervisor");
        }
    }

    @PostMapping("/modifySupervisor")
    public HttpResponseEntity modifySupervisor(Supervisor supervisor) {
        if (supervisorService.modifySupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("Failed to modify supervisor");
        }
    }

    @PostMapping("/selectAllSupervisor")
    public HttpResponseEntity selectAllSupervisor(Integer curPage, Integer pageSize) {
        return HttpResponseEntity.success(supervisorService.selectAllSupervisor(curPage, pageSize));
    }

    @PostMapping("/selectSupervisorByTel")
    public HttpResponseEntity selectSupervisorByTel(Supervisor supervisor) {
        return HttpResponseEntity.success(supervisorService.selectSupervisorByTel(supervisor));
    }

    @PostMapping("/selectSupervisorByName")
    public HttpResponseEntity selectSupervisorByName(Integer curPage, Integer pageSize, Supervisor supervisor) {
        return HttpResponseEntity.success(supervisorService.selectSupervisorByName(curPage, pageSize, supervisor));
    }


}
