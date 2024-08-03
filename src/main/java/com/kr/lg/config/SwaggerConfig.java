package com.kr.lg.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
@Slf4j
public class SwaggerConfig {

    @Bean
    public Docket apiV2() {

        log.info("▶ [스웨거] v1 버전 등록");
        String version  = "v1";
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
