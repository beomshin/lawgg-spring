package com.kr.lg.module.login.service;

import com.kr.lg.module.login.model.dto.GoogleLoginRequestDto;
import com.kr.lg.module.login.model.dto.KakaoLoginRequestDto;
import com.kr.lg.module.login.model.dto.NaverLoginRequestDto;
import com.kr.lg.module.login.model.google.GoogleLoginDto;
import com.kr.lg.module.login.model.kakao.KakaoLoginDto;
import com.kr.lg.module.login.model.naver.NaverLoginDto;

public interface OAuthService {

    GoogleLoginDto googleOAuth(GoogleLoginRequestDto requestDto) throws Exception;
    KakaoLoginDto kakaoOAuth(KakaoLoginRequestDto requestDto) throws Exception;
    NaverLoginDto naverOAuth(NaverLoginRequestDto requestDto) throws Exception;
}
