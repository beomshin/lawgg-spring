package com.kr.lg.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class SwaggerConfig {

    @Bean
    public OpenAPI apiV2() {

        log.info("▶ [Swagger] 등록");
        String version  = "v1";
        String title = "로우지지 API";

        return new OpenAPI()
                .components(new Components())
                .info(apiInfo(title, version));
    }

    private Info apiInfo(String title, String version) {
        return new Info()
                .title(title)
                .version(version)
                .description("https://cground.store/lawgg/");
    }
}
