package com.neusoft.neu6053.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "TestControllerAPI", description = "测试控制器接口")
public class TestController {

    @GetMapping("/testGet")
    public String testGet() {
        return "This is a test GET API.";
    }

    @PostMapping("/testPost")
    public String testPost(@RequestBody String body) {
        return "This is a test POST API. You posted: " + body;
    }
}
