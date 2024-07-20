package com.kr.lg.module.config;

import com.kr.lg.web.filters.DefaultHttpLoggingFilter;
import com.kr.lg.web.filters.DefaultMDCLoggingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@TestConfiguration
public class MockMvcConfig {

    @Autowired
    private WebApplicationContext ctx; // 웹 어플리케이션 컨텍스

    /**
     * spring security 제거
     * @return
     */
    @Bean
    public MockMvc mockMvc() {
        return  MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new DefaultHttpLoggingFilter()) // 로깅 필터
                .addFilter(new DefaultMDCLoggingFilter()) // mdc 필터
                .alwaysDo(print())
                .build();
    }

}
