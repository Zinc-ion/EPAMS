package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.services.SupervisorService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import com.neusoft.neu6053.utils.RedisUtils;
import com.neusoft.neu6053.utils.RoleUtil;
import com.neusoft.neu6053.utils.UUIDUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@RestController
@RequestMapping("/supervisor")
@Tag(name = "SupervisorControllerAPI", description = "监督员相关接口")
public class  SupervisorController {
    /**
     * 使用构造器注入，避免使用@Autowired注解
     * 使用@RequiredArgsConstructor注解，对所有final修饰的成员变量生成构造器
     */
    private final SupervisorService supervisorService;
    private final RedisUtils redisUtils;


    @Operation(
            summary = "supervisor登录接口",
            description = "根据telId和password登录supervisor账户，返回supervisor信息与登录token"
    )
    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody Supervisor supervisor) {
        Supervisor isLogin = supervisorService.loginSupervisor(supervisor);
        if (isLogin != null) {
            //模糊删除redis中重复的token，保证一个账户只有一个token
            redisUtils.deleteKeys("*" + RoleUtil.SUPERVISOR + isLogin.getTelId() + "*");
            //生成token 以supervisor+telId+UUID为token，前缀用于模糊删除
            String token = RoleUtil.SUPERVISOR + isLogin.getTelId() + UUIDUtil.getOneUUID();
            //保存token,key为token,value为AdminId,有效期为1个小时 value加上前缀admin用于区分角色
            redisUtils.set(token, RoleUtil.SUPERVISOR + isLogin.getTelId(), 1, TimeUnit.HOURS);
            //返回值
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put(RoleUtil.SUPERVISOR, isLogin);
            return HttpResponseEntity.success(map);
        } else {
            return HttpResponseEntity.failure("监督员账户或密码错误");
        }
    }


    @Operation(
            summary = "supervisor退出接口",
            description = "根据请求头中token识别对应监督员，删除redis中的token"
    )
    @GetMapping(value = "/logout")
    public HttpResponseEntity logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        //删除redis的token
        redisUtils.del(token);
        return HttpResponseEntity.success("退出成功");
    }


    @Operation(
            summary = "supervisor注册接口",
            description = "注册supervisor账户，返回supervisor对象"
    )
    @PostMapping("/add")
    public HttpResponseEntity addSupervisor(@RequestBody Supervisor supervisor) {
        if (supervisorService.addSupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("添加失败，请更换tel_id后重试");
        }
    }

    @Operation(
            summary = "supervisor删除接口",
            description = "根据tel_id删除supervisor账户，返回删除的supervisor对象"
    )
    @PostMapping("/delete/deleteById")
    public HttpResponseEntity deleteSupervisorById(@RequestBody Supervisor supervisor) {
        if (supervisorService.deleteSupervisorById(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("Failed to delete supervisor, please check the tel_id");
        }
    }

    @Operation(
            summary = "supervisor批量删除接口",
            description = "根据tel_id数组批量删除supervisor账户，返回提示信息"
    )
    //批量删除
    @PostMapping("/delete/deleteByIdGroup")
    public HttpResponseEntity deleteSupervisorByIdGroup(@RequestBody String[] telId) {
        if (supervisorService.deleteSupervisorByIdGroup(telId) ) {
            return HttpResponseEntity.success("success to delete supervisor");
        } else {
            return HttpResponseEntity.failure("Failed to delete supervisor, please check the tel_id");
        }
    }

    @Operation(
            summary = "supervisor修改接口",
            description = "根据tel_id修改supervisor账户，返回修改后的supervisor对象"
    )
    @PostMapping("/modify")
    public HttpResponseEntity modifySupervisor(@RequestBody Supervisor supervisor) {
        if (supervisorService.modifySupervisor(supervisor) == 1) {
            return HttpResponseEntity.success(supervisor);
        } else {
            return HttpResponseEntity.failure("Failed to modify supervisor, please check the tel_id");
        }
    }

    @Operation(
            summary = "supervisor查询接口",
            description = "分页查询所有supervisor账户，返回supervisor列表，pageSize为-1时不分页"
    )
    @PostMapping("/select/selectAll")
    public HttpResponseEntity selectAllSupervisor(@RequestParam Integer curPage, @RequestParam Integer pageSize) {
        return HttpResponseEntity.success(supervisorService.selectAllSupervisor(curPage, pageSize));
    }

    @Operation(
            summary = "supervisor根据Id查询接口",
            description = "根据tel_id查询supervisor账户，返回supervisor对象"
    )
    @PostMapping("/select/selectByTel")
    public HttpResponseEntity selectSupervisorByTel(@RequestBody Supervisor supervisor) {
        return HttpResponseEntity.success(supervisorService.selectSupervisorByTel(supervisor));
    }


    @Operation(
            summary = "supervisor条件查询接口",
            description = "多条件查询，条件包括性别、手机号、姓名（模糊查询）查询supervisor账户，返回supervisor对象，pageSize为-1时不分页"
    )
    //    多条件查询，条件包括性别、手机号、姓名（模糊查询）
    @PostMapping("/select/selectByParams")
    public HttpResponseEntity selectSupervisorByParams(@RequestParam Integer curPage, @RequestParam Integer pageSize, @RequestBody Supervisor supervisor) {
        return HttpResponseEntity.success(supervisorService.selectSupervisorByParams(curPage, pageSize, supervisor));
    }

}
