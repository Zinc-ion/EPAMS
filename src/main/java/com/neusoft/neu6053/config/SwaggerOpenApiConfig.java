package com.neusoft.neu6053.config;


import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

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
                @Server(url = "http://localhost:8085/", description = "本地服务器一服务"),
//                @Server(url = "http://192.168.2.236/demo/", description = "本地服务器二服务"),
        }
//        externalDocs = @ExternalDocumentation(description = "更多内容请查看该链接", url = "xxx")
)
public class SwaggerOpenApiConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {

        return new OpenAPI()
                .components(components())
                // 2. 再在这里添加上Swagger要使用的安全策略
                // addList()中写上对应的key
                .addSecurityItem(new SecurityRequirement().addList("tokenScheme"));
    }
    // 1. 先在组件中注册安全策略
    private Components components(){
        return new Components()
                // 第一个参数是key值，后面是初始化一个安全策略的参数
                .addSecuritySchemes("tokenScheme", new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("token"));
    }
}
