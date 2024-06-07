package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.services.AdminService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import com.neusoft.neu6053.utils.RedisUtils;
import com.neusoft.neu6053.utils.RoleUtil;
import com.neusoft.neu6053.utils.UUIDUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody Admin admin) {
        Admin isLogin = adminService.loginAdmin(admin);
        if (isLogin != null) {
            //生成token
            String token = UUIDUtil.getOneUUID();
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

    @GetMapping(value = "/logout")
    @ResponseBody
    public HttpResponseEntity logout(HttpServletRequest request) {
        String token = request.getHeader("token");
        //删除redis的token
        redisUtils.del(token);
        return HttpResponseEntity.success("退出成功");
    }

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
