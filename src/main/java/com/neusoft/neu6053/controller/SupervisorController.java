package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.services.SupervisorService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/supervisor")
@Tag(name = "SupervisorControllerAPI", description = "监督员相关接口")
public class SupervisorController {
    /**
     * 使用构造器注入，避免使用@Autowired注解
     * 使用@RequiredArgsConstructor注解，对所有final修饰的成员变量生成构造器
     */
    private final SupervisorService supervisorService;


    @PostMapping("/add")
    public HttpResponseEntity addSupervisor(@RequestBody Supervisor supervisor) {
        if (supervisorService.addSupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("添加失败，请更换tel_id后重试");
        }
    }

    @PostMapping("/delete")
    public HttpResponseEntity deleteSupervisor(@RequestBody Supervisor supervisor) {
        if (supervisorService.deleteSupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("Failed to delete supervisor, please check the tel_id");
        }
    }

    //批量删除
    @PostMapping("/deleteById")
    public HttpResponseEntity deleteSupervisorById(@RequestBody String[] telId) {
        if (supervisorService.deleteSupervisorById(telId) ) {
            return HttpResponseEntity.success("success to delete supervisor");
        } else {
            return HttpResponseEntity.failure("Failed to delete supervisor, please check the tel_id");
        }
    }

    @PostMapping("/modify")
    public HttpResponseEntity modifySupervisor(@RequestBody Supervisor supervisor) {
        if (supervisorService.modifySupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("Failed to modify supervisor, please check the tel_id");
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


    //    多条件查询，条件包括性别、手机号、姓名（模糊查询）
    @PostMapping("/selectByParams")
    public HttpResponseEntity selectSupervisorByParams(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Supervisor supervisor) {
        return HttpResponseEntity.success(supervisorService.selectSupervisorByParams(curPage, pageSize, supervisor));
    }

}
