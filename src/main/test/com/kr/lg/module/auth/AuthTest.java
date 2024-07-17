package com.kr.lg.module.auth;

import com.kr.lg.LgWasApplication;
import com.kr.lg.module.auth.service.AuthService;
import com.kr.lg.module.config.MockMvcConfig;
import com.kr.lg.web.jwt.JwtService;
import com.kr.lg.web.provider.common.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

    private final String COOKIE_ID = "_lg_4SD343"; // 리프레쉬 토큰 쿠키 아이디

    @SpyBean
    private AuthService authService;

    @SpyBean
    private JwtService jwtService;

    @Test
    @Transactional
    @DisplayName("토큰 재발급 성공 테스트")
    public void test() throws Exception {

        String accessToken = jwtService.createJwtToken("120", "/", new ArrayList<>());

        mockMvc.perform(get("/api/public/v1/reissue/token").cookie(new Cookie(COOKIE_ID, accessToken)))
              .andExpect(status().isOk())
              .andDo(print());
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
