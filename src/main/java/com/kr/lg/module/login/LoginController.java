package com.kr.lg.module.login;

import com.kr.lg.db.entities.UserTb;
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
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final NaverProp naverProp;
    private final KakaoProp kakaoProp;
    private final GoogleProp googleProp;

    private final UserService userService;
    private final OAuthService oAuthService;
    private final LoginService loginService;

    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) Boolean error, // 로그인 실패
            @RequestParam(value = "message", required = false) String message, // 로그인 실패 메세지
            @CookieValue(value = "savedLoginId", required = false) String savedLoginId, // 아이디 저장
            ModelAndView mav
    ) {
        mav.addObject("savedLoginId", savedLoginId);
        mav.addObject("error", error);
        mav.addObject("message", message);
        mav.setViewName("view/member/login");
        return mav;
    }

    @GetMapping(value = "/google/login")
    @ApiOperation(value = "로우지지 구글 로그인", notes = "로우지지 구글 로그인을 합니다.")
    public RedirectView googleLogin() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(googleProp.googleInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/kakao/login")
    @ApiOperation(value = "로우지지 카카오 로그인", notes = "로우지지 카카오 로그인을 합니다.")
    public RedirectView kakaoLogin() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(kakaoProp.kakaoInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/naver/login")
    @ApiOperation(value = "로우지지 네이버 로그인", notes = "로우지지 네이버 로그인을 합니다.")
    public RedirectView naverLogin() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(naverProp.naverInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/api/public/google/login/redirect")
    @ApiOperation(value = "구글 로그인 결과 콜백", notes = "구글 로그인 결과를 콜백합니다.")
    public RedirectView redirectGoogleLogin(
            @RequestParam(value = "code") String code
    ) throws Exception{
        RedirectView redirectView = new RedirectView();
        GoogleLoginDto googleLoginDto = oAuthService.googleOAuth(new GoogleLoginRequestDto(googleProp, code));
        UserTb userTb = userService.enrollUser(new LoginDto(googleLoginDto));
        redirectView.setUrl(loginService.redirect(userTb));
        return redirectView;
    }

    @GetMapping(value = "/api/public/kakao/login/redirect")
    @ApiOperation(value = "카카오 결과 콜백", notes = "카카오 로그인 결과를 콜백합니다.")
    public RedirectView redirectKakaoLogin(
            @RequestParam(value = "code") String code
    ) throws Exception {
        RedirectView redirectView = new RedirectView();
        KakaoLoginDto kakaoLoginDto = oAuthService.kakaoOAuth(new KakaoLoginRequestDto(kakaoProp, code));
        UserTb userTb = userService.enrollUser(new LoginDto(kakaoLoginDto));
        redirectView.setUrl(loginService.redirect(userTb));
        return redirectView;
    }

    @GetMapping(value = "/api/public/naver/login/redirect")
    @ApiOperation(value = "네이버 로그인 결과 콜백", notes = "네이버 로그인 결과를 콜백합니다.")
    public RedirectView redirectNaverLogin(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "state") String state
    ) throws Exception {
        RedirectView redirectView = new RedirectView();
        NaverLoginDto naverLoginDto = oAuthService.naverOAuth(new NaverLoginRequestDto(naverProp, code, state));
        UserTb userTb = userService.enrollUser(new LoginDto(naverLoginDto));
        redirectView.setUrl(loginService.redirect(userTb));
        return redirectView;
    }

}
