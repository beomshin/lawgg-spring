package com.kr.lg.service.sns;

import com.kr.lg.model.common.sns.naver.NaverLoginDto;
import com.kr.lg.model.common.sns.naver.NaverLoginRequestDto;

public interface NaverService {

    NaverLoginDto naverOAuth(NaverLoginRequestDto requestDto) throws Exception;
}
