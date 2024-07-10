package com.kr.lg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket apiV1() {

        String version  = "v1";
        String title = "로우지지 API " +  version;

        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false) // swagger 응답코드 기본 메세지 disable
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kr.lg.controller"))
                .build()
                .apiInfo(apiInfo(title, version));
    }

    @Bean
    public Docket apiV2() {

        String version  = "v2";
        String title = "로우지지 API " +  version;

        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kr.lg.module"))
                .build()
                .apiInfo(apiInfo(title, version));
    }


    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfoBuilder()
                .title(title)
                .version(version)
                .termsOfServiceUrl("https://cground.store/lawgg/")
                .build();
    }
}
