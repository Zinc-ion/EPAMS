package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.dao.entity.Inspector;
import com.neusoft.neu6053.services.InspectorService;
import com.neusoft.neu6053.utils.HttpResponseEntity;
import com.neusoft.neu6053.utils.RedisUtils;
import com.neusoft.neu6053.utils.RoleUtil;
import com.neusoft.neu6053.utils.UUIDUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inspector")
@Tag(name = "InspectorControllerAPI", description = "网格员相关接口")
public class InspectorController {
    private final InspectorService inspectorService;
    private final RedisUtils redisUtils;

    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody Inspector inspector) {
        Inspector isLogin = inspectorService.loginInspector(inspector);
        if (isLogin != null) {
            //生成token
            String token = UUIDUtil.getOneUUID();
            //保存token,key为token,value为AdminId,有效期为1个小时 value加上前缀inspector用于区分角色
            redisUtils.set(token, RoleUtil.INSPECTOR + isLogin.getTelId(), 1, TimeUnit.HOURS);
            //返回值
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put(RoleUtil.INSPECTOR, isLogin);
            return HttpResponseEntity.success(map);
        } else {
            return HttpResponseEntity.failure("网格员账户或密码错误");
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
