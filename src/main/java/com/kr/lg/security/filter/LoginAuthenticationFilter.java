package com.kr.lg.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.lg.security.dto.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public LoginAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler, String filterProcessesUrl) {
        super(authenticationManager); // spring security authentication manager 등록 (manager 가 provider 실행함)
        this.setAuthenticationSuccessHandler(authenticationSuccessHandler); // 성공 핸들러
        this.setAuthenticationFailureHandler(authenticationFailureHandler); // 실패 핸들러
        this.setFilterProcessesUrl(filterProcessesUrl); // path 설정
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {

            log.info("▶ [Spring Security 로그인][LoginAuthenticationFilter] 1. 로그인 정보 데이터 누락 체크");
            LoginRequest credential = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            if (credential == null || credential.isRequestBlank()) throw new AuthenticationServiceException("로그인 정보가 누락되었습니다.");
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credential.getLoginId(), credential.getPassword()));
        } catch (IOException e) {
            log.error("▶ [Spring Security 로그인][LoginAuthenticationFilter] IoException", e);
            throw new RuntimeException(e);
        }
    }

}
