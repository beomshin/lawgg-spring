package com.kr.lg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@EnableAsync
public class LgWasApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LgWasApplication.class, args);
    }

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(LgWasApplication.class);
    }
}
