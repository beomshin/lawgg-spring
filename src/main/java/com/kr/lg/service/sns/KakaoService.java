package com.kr.lg.service.sns;

import com.kr.lg.web.common.sns.kakao.KakaoLoginDto;
import com.kr.lg.web.common.sns.kakao.KakaoLoginRequestDto;

public interface KakaoService {

    KakaoLoginDto kakaoOAuth(KakaoLoginRequestDto requestDto) throws Exception;
}
