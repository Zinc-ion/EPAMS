package com.neusoft.neu6053.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringBootConfiguration;

@SpringBootConfiguration
@OpenAPIDefinition(
        // ## API的基本信息，包括标题、版本号、描述、联系人等
        info = @Info(
                title = "EPAMS-Swagger3接口文档",       // Api接口文档标题（必填）
                description = "NEU605.3小组项目",      // Api接口文档描述
                version = "1.2.1",                                   // Api接口版本
//                termsOfService = "https://example.com/",             // Api接口的服务条款地址
//                contact = @Contact(
//                        name = "蚂蚁小哥",                            // 作者名称
//                        email = "xiaofeng@qq.com",                  // 作者邮箱
//                        url = "https://www.cnblogs.com/antLaddie/"  // 介绍作者的URL地址
//                ),
                license = @License(                                                // 设置联系人信息
                        name = "Apache 2.0",                                       // 授权名称
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"   // 授权信息
                )
        ),
        // ## 表示服务器地址或者URL模板列表，多个服务地址随时切换（只不过是有多台IP有当前的服务API）
        servers = {
                @Server(url = "http://localhost/", description = "本地服务器一服务"),
//                @Server(url = "http://192.168.2.236/demo/", description = "本地服务器二服务"),
        }
//        externalDocs = @ExternalDocumentation(description = "更多内容请查看该链接", url = "xxx")
)
public class SwaggerOpenApiConfig {
}
