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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) Boolean error, // 로그인 실패
            @RequestParam(value = "message", required = false) String message, // 로그인 실패 메세지
            @CookieValue(value = "savedLoginId", required = false) String savedLoginId, // 아이디 저장
//            @ApiParam(value = "로그인 세션 유저 정보")
            @AuthUser UserTb userTb,
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
//    @ApiOperation(value = "로우지지 구글 로그인", notes = "로우지지 구글 로그인을 합니다.")
    public RedirectView googleLogin() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(googleProp.googleInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/kakao/login")
//    @ApiOperation(value = "로우지지 카카오 로그인", notes = "로우지지 카카오 로그인을 합니다.")
    public RedirectView kakaoLogin() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(kakaoProp.kakaoInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/naver/login")
//    @ApiOperation(value = "로우지지 네이버 로그인", notes = "로우지지 네이버 로그인을 합니다.")
    public RedirectView naverLogin() {
        RedirectView redirectView = new RedirectView();
        redirectView.setStatusCode(HttpStatus.SEE_OTHER);
        redirectView.setUrl(naverProp.naverInitUrl());
        return redirectView;
    }

    @GetMapping(value = "/google/login/callback")
//    @ApiOperation(value = "구글 로그인 결과 콜백", notes = "구글 로그인 결과를 콜백합니다.")
    public ModelAndView redirectGoogleLogin(
            @RequestParam(value = "code") String code,
            ModelAndView mav
    ) throws Exception{
        GoogleLoginDto googleLoginDto = oAuthService.googleOAuth(new GoogleLoginRequestDto(googleProp, code));
        UserTb userTb = userService.enrollUser(new LoginDto(googleLoginDto));
        userService.updateSessionUserTb(userTb);
        mav.setViewName("redirect:/");
        return mav;
    }

    @GetMapping(value = "/kakao/login/callback")
//    @ApiOperation(value = "카카오 결과 콜백", notes = "카카오 로그인 결과를 콜백합니다.")
    public ModelAndView redirectKakaoLogin(
            @RequestParam(value = "code") String code,
            ModelAndView mav
    ) throws Exception {
        KakaoLoginDto kakaoLoginDto = oAuthService.kakaoOAuth(new KakaoLoginRequestDto(kakaoProp, code));
        UserTb userTb = userService.enrollUser(new LoginDto(kakaoLoginDto));
        userService.updateSessionUserTb(userTb);
        mav.setViewName("redirect:/");
        return mav;
    }

    @GetMapping(value = "/naver/login/callback")
//    @ApiOperation(value = "네이버 로그인 결과 콜백", notes = "네이버 로그인 결과를 콜백합니다.")
    public ModelAndView redirectNaverLogin(
            @RequestParam(value = "code") String code,
            @RequestParam(value = "state") String state,
            ModelAndView mav
    ) throws Exception {
        NaverLoginDto naverLoginDto = oAuthService.naverOAuth(new NaverLoginRequestDto(naverProp, code, state));
        UserTb userTb = userService.enrollUser(new LoginDto(naverLoginDto));
        userService.updateSessionUserTb(userTb);
        mav.setViewName("redirect:/");
        return mav;
    }

}
