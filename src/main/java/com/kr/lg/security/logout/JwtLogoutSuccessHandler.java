package com.kr.lg.security.logout;


import com.kr.lg.common.enums.entity.type.SnsType;
import com.kr.lg.module.auth.excpetion.AuthException;
import com.kr.lg.module.auth.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {

    private final JwtService jwtService;

    @Value("${lg.logout.url}")
    String homeUrl;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("▶ [Spring Security 로그인][JwtLogoutSuccessHandler] 로그아웃 성공");
        String token = request.getParameter("token");
        SnsType type = null;

        try {
            type = SnsType.of(Integer.parseInt(jwtService.getType(token)));
        } catch (AuthException e) {}

        switch (type) {
            case LG_SNS_TYPE:
                log.info("[로우지지 로그인 타입 로그아웃]");
                response.sendRedirect(homeUrl);
                break;
            case NAVER_SNS_TYPE:
                log.info("[네이버 로그인 타입 로그아웃]");
                response.sendRedirect(homeUrl);
                break;
            case GOOGLE_SNS_TYPE:
                log.info("[구글 로그인 타입 로그아웃]");
                response.sendRedirect(homeUrl);
                break;
            case KAKAO_SNS_TYPE:
                log.info("[카카오 로그인 타입 로그아웃]");
                response.sendRedirect(homeUrl);
                break;
            default:
                response.sendRedirect(homeUrl);
                break;
        }
    }

}
