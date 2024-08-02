package com.kr.lg.module.login.service;

import com.kr.lg.module.login.model.naver.NaverLoginDto;
import com.kr.lg.module.login.model.naver.NaverLoginRequestDto;

public interface NaverService {

    NaverLoginDto naverOAuth(NaverLoginRequestDto requestDto) throws Exception;
}
