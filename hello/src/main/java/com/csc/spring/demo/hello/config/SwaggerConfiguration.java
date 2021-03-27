package com.csc.spring.demo.hello.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/27 23:09
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Value("${swagger.enabled}")
    private Boolean enabled;

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.csc.spring.demo.hello"))
                .paths(PathSelectors.any())
                .build()
                // TODO: config basePath
                // .pathProvider()
                ;


        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-demo title")
                .description("spring-demo description")
                .contact(new Contact("csc", "git", "email"))
                .termsOfServiceUrl("http://localhost:8081/")
                .version("1.0")
                .build();
    }
}
