package com.afeng.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Description:
 * @Author hugf
 * @Date 2019/3/4
 * @Time 11:03
 */
@Configuration //标记配置类
public class Swagger2Config {
    /**
     * 添加摘要信息(Docket)
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.afeng.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("描述：欢迎来到枫之阁接口文档页")
                .termsOfServiceUrl("http://blog.didispace.com/")
                .contact(new Contact("枫之阁","https://iamafeng.github.io/","hugf@z-code.cn"))
                .version("1.0")
                .build();
    }
//    @Bean
//    public Docket controllerApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(new ApiInfoBuilder()
//                        .title("Spring Boot中使用Swagger2构建RESTful APIs")
//                        .description("描述：欢迎来到枫之阁接口文档页")
//                        .contact(new Contact("枫之阁","https://iamafeng.github.io/","hugf@z-code.cn"))
//                        .version("版本号:1.0")
//                        .build())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.afeng.api.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
}
