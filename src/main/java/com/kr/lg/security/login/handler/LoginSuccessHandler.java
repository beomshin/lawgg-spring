package com.kr.lg.security.login.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("▶ [Spring Security 로그인][LoginSuccessHandler] 4. 로그인 성공 핸들러 ==========> ");

        setDefaultTargetUrl("https://cgroud.store/lawgg/");

        super.clearAuthenticationAttributes(request);

        saveLoginRedirectPage(request, response); // 로그인 후 리다이렉트 페이지 세팅
        response.addCookie(saveLoginId(request.getParameter("loginId"), request.getParameter("remember-id"))); // 아이디 저장 플래그 설정

        super.onAuthenticationSuccess(request, response, authentication);
    }

    private Cookie saveLoginId(String loginId, String rememberId) {
        if (StringUtils.isNotBlank(rememberId) && rememberId.equals("on")) {
            Cookie cookie = new Cookie("savedLoginId", loginId);
            cookie.setMaxAge(60 * 60 * 24 * 30); // 30일 동안 쿠키 유지
            return cookie;
        } else {
            Cookie cookie = new Cookie("savedLoginId", null);
            cookie.setMaxAge(0); // 쿠키 삭제
            return cookie;
        }
    }

    private void saveLoginRedirectPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if(savedRequest != null){
            String url = savedRequest.getRedirectUrl();
            if(StringUtils.isBlank(url) && url.contains("/account/login")) {
                url = "/";
            }
            requestCache.removeRequest(request, response);
            getRedirectStrategy().sendRedirect(request, response, url);
        }
    }

}
