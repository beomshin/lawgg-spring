package com.kr.lg.module.login;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.model.annotation.AuthUser;
import com.kr.lg.module.login.model.dto.LoginDto;
import com.kr.lg.module.login.model.google.GoogleLoginDto;
import com.kr.lg.module.login.model.dto.GoogleLoginRequestDto;
import com.kr.lg.module.login.model.google.GoogleProp;
import com.kr.lg.module.login.model.kakao.KakaoLoginDto;
import com.kr.lg.module.login.model.dto.KakaoLoginRequestDto;
import com.kr.lg.module.login.model.kakao.KakaoProp;
import com.kr.lg.module.login.model.naver.NaverLoginDto;
import com.kr.lg.module.login.model.dto.NaverLoginRequestDto;
import com.kr.lg.module.login.model.naver.NaverProp;
import com.kr.lg.module.login.service.*;
import com.kr.lg.module.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Slf4j
@Controller
@RequiredArgsConstructor
@Tag(name = "LoginController", description = "로그인 컨트롤러")
public class LoginController {

    private final NaverProp naverProp;
    private final KakaoProp kakaoProp;
    private final GoogleProp googleProp;

    private final UserService userService;
    private final OAuthService oAuthService;

    @Value("${lg.redirect.url.main}")
    String lgRedirectMainUrl;

    @GetMapping("/login")
    @Operation(summary = "로그인 하기", description = "로그인 합니다.")
    public ModelAndView login(
            @Parameter(description = "로그인 실패 플래그") @RequestParam(value = "error", required = false) Boolean error, // 로그인 실패
            @Parameter(description = "로그인 실패 메세지") @RequestParam(value = "message", required = false) String message, // 로그인 실패 메세지
            @Parameter(description = "아이디 저장정보") @CookieValue(value = "savedLoginId", required = false) String savedLoginId, // 아이디 저장
            @Parameter(description = "로그인 세션 유저 정보") @AuthUser UserTb userTb,
            ModelAndView mav
    ) {
        mav.addObject("savedLoginId", savedLoginId);
        mav.addObject("error", error);
        mav.addObject("message", message);
        if (userTb != null) {
            mav.setViewName("redirect:/");
        } else {
            mav.setViewName("view/member/login");
        }
        return mav;
    }

    @GetMapping(value = "/google/login")
    @Operation(summary = "로우지지 구글 로그인", description = "로우지지 구글 로그인을 합니다.")
    public RedirectView googleLogin() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(googleProp.googleInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/kakao/login")
    @Operation(summary = "로우지지 카카오 로그인", description = "로우지지 카카오 로그인을 합니다.")
    public RedirectView kakaoLogin() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(kakaoProp.kakaoInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/naver/login")
    @Operation(summary = "로우지지 네이버 로그인", description = "로우지지 네이버 로그인을 합니다.")
    public RedirectView naverLogin() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(naverProp.naverInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/google/login/callback")
    @Operation(summary = "구글 로그인 결과 콜백", description = "구글 로그인 결과를 콜백합니다.")
    public ModelAndView redirectGoogleLogin(
            @Parameter(description = "구글 로그인 콜백 코드") @RequestParam(value = "code") String code,
            ModelAndView mav
    ) throws Exception{
        GoogleLoginDto googleLoginDto = oAuthService.googleOAuth(new GoogleLoginRequestDto(googleProp, code));
        UserTb userTb = userService.enrollUser(new LoginDto(googleLoginDto));
        userService.updateSessionUserTb(userTb);
        mav.setViewName("redirect:" + lgRedirectMainUrl);
        return mav;
    }

    @GetMapping(value = "/kakao/login/callback")
    @Operation(summary = "카카오 결과 콜백", description = "카카오 로그인 결과를 콜백합니다.")
    public ModelAndView redirectKakaoLogin(
            @Parameter(description = "카카오 로그인 콜백 코드") @RequestParam(value = "code") String code,
            ModelAndView mav
    ) throws Exception {
        KakaoLoginDto kakaoLoginDto = oAuthService.kakaoOAuth(new KakaoLoginRequestDto(kakaoProp, code));
        UserTb userTb = userService.enrollUser(new LoginDto(kakaoLoginDto));
        userService.updateSessionUserTb(userTb);
        mav.setViewName("redirect:" + lgRedirectMainUrl);
        return mav;
    }

    @GetMapping(value = "/naver/login/callback")
    @Operation(summary = "네이버 로그인 결과 콜백", description = "네이버 로그인 결과를 콜백합니다.")
    public ModelAndView redirectNaverLogin(
            @Parameter(description = "네이버 로그인 콜백 코드") @RequestParam(value = "code") String code,
            @Parameter(description = "네이버 로그인 콜백 상태") @RequestParam(value = "state") String state,
            ModelAndView mav
    ) throws Exception {
        NaverLoginDto naverLoginDto = oAuthService.naverOAuth(new NaverLoginRequestDto(naverProp, code, state));
        UserTb userTb = userService.enrollUser(new LoginDto(naverLoginDto));
        userService.updateSessionUserTb(userTb);
        mav.setViewName("redirect:" + lgRedirectMainUrl);
        return mav;
    }

}
