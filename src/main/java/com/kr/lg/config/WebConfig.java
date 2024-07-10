package com.kr.lg.config;

import com.kr.lg.common.convert.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new WebBoardTypeEnumConvert());
        registry.addConverter(new WebDepthEnumConvert());
        registry.addConverter(new WebLineEnumConvert());
        registry.addConverter(new WebTrialSubjectEnumConvert());
        registry.addConverter(new WebTrialTopicEnumConvert());
        WebMvcConfigurer.super.addFormatters(registry);
    }

}
