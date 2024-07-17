package com.kr.lg.module.main;

import com.kr.lg.LgWasApplication;
import com.kr.lg.module.config.MockMvcConfig;
import com.kr.lg.module.main.service.MainService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest(
        classes = LgWasApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Import(MockMvcConfig.class) // Test config import 설정 (커스텀 mockMvc)
public class MainTest {

    @Autowired
    private MockMvc mockMvc;

    @SpyBean
    private MainService mainService;

    @Test
    @Transactional
    @DisplayName("메인 페이지 정보 조회 테스트")
    public void test() throws Exception {
        mockMvc.perform(get("/api/public/v1/find/main"))
              .andExpect(status().isOk())
              .andDo(print());
    }


    @Test
    @Transactional
    @DisplayName("메인 페이지 정보 조회 RuntimeException 테스트")
    public void test2() throws Exception {

        when(mainService.getMainPostBoards()).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/public/v1/find/main"))
                .andExpect(status().isInternalServerError())
                .andDo(print());
    }
}
