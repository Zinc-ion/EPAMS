package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.services.AdminService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import com.neusoft.neu6053.utils.RedisUtils;
import com.neusoft.neu6053.utils.RoleUtil;
import com.neusoft.neu6053.utils.UUIDUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admins")
@Tag(name = "AdminControllerAPI", description = "管理员相关接口")
public class AdminController {
    /**
     * 使用构造器注入，避免使用@Autowired注解
     * 使用@RequiredArgsConstructor注解，对所有final修饰的成员变量生成构造器
     */
    private final AdminService adminService;
    private final RedisUtils redisUtils;

    @Operation(
            summary = "admin登录接口",
            description = "根据adminCode和password登录admin账户，返回admin信息与登录token"
    )
    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody Admin admin) {
        Admin isLogin = adminService.loginAdmin(admin);
        if (isLogin != null) {
            //模糊删除redis中重复的token，保证一个账户只有一个token
            redisUtils.deleteKeys("*" + RoleUtil.ADMIN + isLogin.getAdminId() + "*");
            //生成token 以admin+adminId+UUID为token，前缀用于模糊删除
            String token = RoleUtil.ADMIN + isLogin.getAdminId() + UUIDUtil.getOneUUID();
            //保存token,key为token,value为AdminId,有效期为1个小时 value加上前缀admin用于区分角色
            redisUtils.set(token, RoleUtil.ADMIN + isLogin.getAdminId(), 1, TimeUnit.HOURS);
            //返回值
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put(RoleUtil.ADMIN, isLogin);
            return HttpResponseEntity.success(map);
        } else {
            return HttpResponseEntity.failure("管理员账户或密码错误");
        }
    }

    @Operation(
            summary = "admin登出接口",
            description = "根据请求头中token信息识别admin并登出，删除redis中的token信息"
    )
    @GetMapping(value = "/logout")
    @ResponseBody
    public HttpResponseEntity logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        //删除redis的token
        redisUtils.del(token);
        return HttpResponseEntity.success("退出成功");
    }


    @Operation(
            summary = "admin注册接口",
            description = "注册admin，adminCode唯一，返回注册的admin信息"
    )
    @PostMapping("/add")
    public HttpResponseEntity addAdmin(@RequestBody Admin admin) {
        if(adminService.selectAdminByCode(admin) != null) {                 //查询账户名是否重复
            return HttpResponseEntity.failure("Admin code already exists");
        }
        if (1 == adminService.saveAdmin(admin)) {
            return HttpResponseEntity.success(admin);
        } else {
            return HttpResponseEntity.failure("Failed to save admin");
        }
    }
}
