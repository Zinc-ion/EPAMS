package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "AdminControllerAPI", description = "管理员相关接口")
public class AdminController {
    @Autowired
    private com.neusoft.neu6053.services.AdminService adminService;

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody Admin admin) {
        List<Admin> isLogin = adminService.loginAdmin(admin);
        if (!isLogin.isEmpty()) {
            return HttpResponseEntity.success(isLogin);
        } else {
            return HttpResponseEntity.failure("Invalid admin code or password");
        }
    }
}
