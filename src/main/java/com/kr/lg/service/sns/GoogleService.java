package com.kr.lg.service.sns;

import com.kr.lg.model.common.sns.google.GoogleLoginDto;
import com.kr.lg.model.common.sns.google.GoogleLoginRequestDto;

public interface GoogleService {

    GoogleLoginDto googleOAuth(GoogleLoginRequestDto requestDto) throws Exception;
}
