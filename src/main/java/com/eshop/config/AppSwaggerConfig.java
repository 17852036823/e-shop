package com.eshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //开启Swagger2注解
public class AppSwaggerConfig {

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("网上商城电子钱包Api")
                .contact("e-shop")
                .description("本文的记录了后端的所有开发接口，方便前端开发人员使用")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .build();
    }

    @Bean("控制层")
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eshop.controller"))
                .paths(PathSelectors.any())
                .build();
    }

}
