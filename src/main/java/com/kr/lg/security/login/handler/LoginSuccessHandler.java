package com.kr.lg.security.login.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.lg.security.dto.LoginResponse;
import com.kr.lg.module.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.debug("▶ [Spring Security 로그인][LoginSuccessHandler] 5. 로그인 성공 핸들러 ==========> ");
        String userId = authentication.getName();
        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        LoginResponse res = LoginResponse.builder()
                .accessToken(jwtService.createJwtToken(userId, request.getRequestURI(), roles))
                .refreshToken(jwtService.createRefreshToken(userId, request.getRequestURI(), roles))
                .build();

        response.setContentType(MediaType.APPLICATION_JSON_VALUE); // content-type json
        new ObjectMapper().writeValue(response.getOutputStream(), res);
    }

}
