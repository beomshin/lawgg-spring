package com.kr.lg.controller.sns;

import com.kr.lg.model.common.sns.google.GoogleProp;
import com.kr.lg.model.common.sns.google.GoogleLoginDto;
import com.kr.lg.model.common.sns.google.GoogleLoginRequestDto;
import com.kr.lg.model.common.sns.lg.LgLoginDto;
import com.kr.lg.service.sns.GoogleService;
import com.kr.lg.service.sns.LgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@Slf4j
public class GoogleController {

    private final GoogleProp googleProp;
    private final GoogleService googleService;
    private final LgService lgService;

    @Value("${lg.redirect.url.login}")
    String lgRedirectLoginUrl;

    @GetMapping(value = "/api/public/google/login")
    public RedirectView moveGoogleInitUrl() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(googleProp.googleInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/api/public/google/login/redirect")
    public RedirectView redirectGoogleLogin(@RequestParam(value = "code") String code) throws Exception{
        RedirectView redirectView = new RedirectView();
        try {
            GoogleLoginDto googleLoginDto = googleService.googleOAuth(new GoogleLoginRequestDto(googleProp, code));
            String loginUrl = lgService.LgLogin(new LgLoginDto(googleLoginDto));
            redirectView.setUrl(loginUrl);
            return redirectView;
        } catch (Exception e) {
            log.error("{}", e);
            redirectView.setUrl(lgRedirectLoginUrl);
            return redirectView;
        }
    }

}
