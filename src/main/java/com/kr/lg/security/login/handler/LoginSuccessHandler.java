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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("▶ [Spring Security 로그인][LoginSuccessHandler] 5. 로그인 성공 핸들러 ==========> ");

        setDefaultTargetUrl("/");

        super.clearAuthenticationAttributes(request);

        RequestCache requestCache = new HttpSessionRequestCache();
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if(savedRequest != null){
            String url = savedRequest.getRedirectUrl();
            if(StringUtils.isNotBlank(url)){
                url = "/";
            } else if(url.contains("/register")){
                url = "/";
            } else if(url.contains("/account/login")){
                url = "/";
            }
            requestCache.removeRequest(request, response);
            getRedirectStrategy().sendRedirect(request, response, url);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
