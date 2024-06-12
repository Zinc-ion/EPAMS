package com.neusoft.neu6053.controller;

import com.neusoft.neu6053.dao.entity.Admin;
import com.neusoft.neu6053.dao.entity.Inspector;
import com.neusoft.neu6053.dao.entity.Supervisor;
import com.neusoft.neu6053.services.InspectorService;
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
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@RestController
@RequestMapping("/inspector")
@CrossOrigin(origins = "http://localhost:8081")
@Tag(name = "InspectorControllerAPI", description = "网格员相关接口")
public class InspectorController {
    private final InspectorService inspectorService;
    private final RedisUtils redisUtils;

    @Operation(
            summary = "inspector登录接口",
            description = "根据telId和password登录inspector账户，返回inspector信息与登录token"
    )
    @PostMapping("/login")
    public HttpResponseEntity login(@RequestBody Inspector inspector) {
        Inspector isLogin = inspectorService.loginInspector(inspector);
        if (isLogin != null) {
            //模糊删除redis中重复的token，保证一个账户只有一个token
            redisUtils.deleteKeys("*" + RoleUtil.INSPECTOR + isLogin.getTelId() + "*");
            //生成token 以inspector+telId+UUID为token，前缀用于模糊删除
            String token = RoleUtil.INSPECTOR + isLogin.getTelId() + UUIDUtil.getOneUUID();
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

    @Operation(
            summary = "inspector退出接口",
            description = "根据请求头中token识别对应网格员，删除redis中的token"
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
            summary = "inspector注册接口",
            description = "注册inspector账户，返回inspector对象"
    )
    @PostMapping("/add")
    public HttpResponseEntity addInspector(@RequestBody Inspector inspector) {
        if (inspectorService.saveInspector(inspector) == 1) {
            return HttpResponseEntity.success(inspector);
        } else {
            return HttpResponseEntity.failure("添加失败，请更换tel_id后重试");
        }
    }

    @Operation(
            summary = "inspector查询接口",
            description = "根据tel_id查询inspector账户，返回inspector对象"
    )
    @PostMapping("/select/selectByTel")
    public HttpResponseEntity selectInspectorByTel(@RequestBody Inspector inspector) {
        return HttpResponseEntity.success(inspectorService.selectInspectorByTel(inspector));
    }


    @Operation(
            summary = "inspector条件查询接口",
            description = "根据省份和城市查询inspector账户，返回inspector对象"
    )
    @PostMapping("/select/selectByProvinceAndCity")
    public HttpResponseEntity selectInspectorByProvinceAndCity(@RequestBody Inspector inspector) {
        return HttpResponseEntity.success(inspectorService.selectInspectorByProvinceAndCity(inspector));
    }

    @Operation(
            summary = "inspector删除接口",
            description = "根据tel_id删除inspector账户，返回删除的inspector对象"
    )
    @PostMapping("/delete/deleteById")
    public HttpResponseEntity deleteInspectorById(@RequestBody Inspector inspector) {
        if (inspectorService.deleteInspectorByTel(inspector) == 1) {
            return HttpResponseEntity.success(inspector);
        } else {
            return HttpResponseEntity.failure("删除网格员失败，请检查tel_id");
        }
    }

    @Operation(
            summary = "inspector批量删除接口",
            description = "根据tel_id数组批量删除inspector账户，返回提示信息"
    )
    //批量删除
    @PostMapping("/delete/deleteByIdGroup")
    public HttpResponseEntity deleteInspectorByIdGroup(@RequestBody String[] telId) {
        if (inspectorService.deleteInspectorByTelGroup(telId) ) {
            return HttpResponseEntity.success("批量删除网格员成功");
        } else {
            return HttpResponseEntity.failure("批量删除网格员失败，请检查tel_id");
        }
    }


    @Operation(
            summary = "inspector修改接口",
            description = "根据tel_id修改inspector账户，返回修改后的inspector对象"
    )
    @PostMapping("/modify")
    public HttpResponseEntity modifyInspector(@RequestBody Inspector inspector) {
        if (inspectorService.modifyInspector(inspector) == 1) {
            return HttpResponseEntity.success(inspector);
        } else {
            return HttpResponseEntity.failure("修改网格员信息失败，请检查tel_id");
        }
    }


    
}
