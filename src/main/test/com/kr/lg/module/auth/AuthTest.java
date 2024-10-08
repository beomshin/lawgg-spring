package com.kr.lg.module.auth;

import com.kr.lg.LgWasApplication;
import com.kr.lg.module.config.MockMvcConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(
        classes = LgWasApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Import(MockMvcConfig.class) // Test config import 설정 (커스텀 mockMvc)
public class AuthTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${cookies.auth.refresh}")
    private String COOKIE_ID;

    @Test
    @Transactional
    @DisplayName("토큰 재발급 성공 테스트")
    public void test() throws Exception {


    }


    @Test
    @Transactional
    @DisplayName("토큰 재발급 유효성 검사 실패 테스트")
    public void test2() throws Exception {

        mockMvc.perform(get("/api/public/v1/reissue/token").cookie(new Cookie(COOKIE_ID, "fail token")))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }


    @Test
    @Transactional
    @DisplayName("토큰 재발급 파라미터 빈값 테스트")
    public void test3() throws Exception {

        mockMvc.perform(get("/api/public/v1/reissue/token").cookie(new Cookie(COOKIE_ID, null)))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }


}
