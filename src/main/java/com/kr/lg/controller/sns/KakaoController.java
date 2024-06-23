package com.kr.lg.controller.sns;

import com.kr.lg.web.common.sns.kakao.KakaoLoginDto;
import com.kr.lg.web.common.sns.kakao.KakaoLoginRequestDto;
import com.kr.lg.web.common.sns.kakao.KakaoProp;
import com.kr.lg.web.common.sns.lg.LgLoginDto;
import com.kr.lg.service.sns.KakaoService;
import com.kr.lg.service.sns.LgService;
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
public class KakaoController {

    private final KakaoProp kakaoProp;
    private final KakaoService kakaoService;
    private final LgService lgService;
    @Value("${lg.redirect.url.login}")
    String lgRedirectLoginUrl;

    @Value("${lg.redirect.url.home}")
    String lgRedirectHomeUrl;

    @GetMapping(value = "/api/public/kakao/login")
    public RedirectView moveGoogleInitUrl() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(kakaoProp.kakaoInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/api/public/kakao/login/redirect")
    public RedirectView redirectKakaoLogin(@RequestParam(value = "code") String code) {
        RedirectView redirectView = new RedirectView();
        try {
            KakaoLoginDto kakaoLoginDto = kakaoService.kakaoOAuth(new KakaoLoginRequestDto(kakaoProp, code));
            String loginUrl = lgService.LgLogin(new LgLoginDto(kakaoLoginDto));
            redirectView.setUrl(loginUrl);
            return redirectView;
        } catch (Exception e) {
            log.error("", e);
            redirectView.setUrl(lgRedirectLoginUrl);
            return redirectView;
        }
    }

    @GetMapping(value = "/api/public/kakao/logout/redirect")
    public RedirectView redirectKakaoLogout() {
        RedirectView redirectView = new RedirectView();
        try {
            redirectView.setUrl(lgRedirectHomeUrl);
            return redirectView;
        } catch (Exception e) {
            log.error("", e);
            redirectView.setUrl(lgRedirectLoginUrl);
            return redirectView;
        }
    }
}
