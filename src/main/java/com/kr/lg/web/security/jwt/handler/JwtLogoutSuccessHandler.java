package com.kr.lg.web.security.jwt.handler;


import com.kr.lg.enums.SnsEnum;
import com.kr.lg.web.jwt.JwtService;
//import com.kr.lg.modules.snsService.model.google.GoogleProp;
//import com.kr.lg.modules.snsService.model.kakao.KakaoProp;
//import com.kr.lg.modules.snsService.model.naver.NaverProp;
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
        log.debug("[JwtLogoutSuccessHandler] 로그아웃 성공 ============>");
        String token = request.getParameter("token");

        switch (SnsEnum.of(Integer.parseInt(jwtService.getType(token)))) {
            case LG_SNS_TYPE:
                log.debug("[로우지지 로그인 타입 로그아웃]");
                response.sendRedirect(homeUrl);
                break;
            case NAVER_SNS_TYPE:
                log.debug("[네이버 로그인 타입 로그아웃]");
                response.sendRedirect(homeUrl);
                break;
            case GOOGLE_SNS_TYPE:
                log.debug("[구글 로그인 타입 로그아웃]");
                response.sendRedirect(homeUrl);
                break;
            case KAKAO_SNS_TYPE:
                log.debug("[카카오 로그인 타입 로그아웃]");
                response.sendRedirect(homeUrl);
                break;
        }
    }

}
