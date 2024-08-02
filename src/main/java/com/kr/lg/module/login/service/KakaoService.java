package com.kr.lg.module.login.service;

import com.kr.lg.module.login.model.kakao.KakaoLoginDto;
import com.kr.lg.module.login.model.kakao.KakaoLoginRequestDto;

public interface KakaoService {

    KakaoLoginDto kakaoOAuth(KakaoLoginRequestDto requestDto) throws Exception;
}
