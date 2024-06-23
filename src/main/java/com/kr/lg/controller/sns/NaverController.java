package com.kr.lg.controller.sns;

import com.kr.lg.web.common.sns.lg.LgLoginDto;
import com.kr.lg.web.common.sns.naver.NaverLoginDto;
import com.kr.lg.web.common.sns.naver.NaverLoginRequestDto;
import com.kr.lg.web.common.sns.naver.NaverProp;
import com.kr.lg.service.sns.LgService;
import com.kr.lg.service.sns.NaverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NaverController {

    private final NaverService naverService;
    private final NaverProp naverProp;
    private final LgService lgService;
    @Value("${lg.redirect.url.login}")
    String lgRedirectLoginUrl;

    @GetMapping(value = "/api/public/naver/login")
    public RedirectView moveGoogleInitUrl() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(naverProp.naverInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/api/public/naver/login/redirect")
    public RedirectView redirectNaverLogin(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "state") String state
    ) throws Exception {
        RedirectView redirectView = new RedirectView();
        try {
            NaverLoginDto naverLoginDto = naverService.naverOAuth(new NaverLoginRequestDto(naverProp, code, state));
            String loginUrl = lgService.LgLogin(new LgLoginDto(naverLoginDto));
            redirectView.setUrl(loginUrl);
            return redirectView;
        } catch (Exception e) {
            log.error("{}", e);
            redirectView.setUrl(lgRedirectLoginUrl);
            return redirectView;
        }
    }
}
