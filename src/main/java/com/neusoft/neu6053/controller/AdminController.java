package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.services.AdminService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import com.neusoft.neu6053.utils.RedisUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admins")
@Tag(name = "AdminControllerAPI", description = "管理员相关接口")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody Admin admin) {
        List<Admin> isLogin = adminService.loginAdmin(admin);
        if (!isLogin.isEmpty()) {
            //生成token
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            //保存token,key为token,value为AdminId,有效期为1个小时
            redisUtils.set(token, isLogin.get(0).getAdminId(), 1, TimeUnit.HOURS);
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("admin", isLogin.get(0));
            return HttpResponseEntity.success(map);
        } else {
            return HttpResponseEntity.failure("Invalid admin code or password");
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
}
