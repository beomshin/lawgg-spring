package com.kr.lg.module.login.service;

import com.kr.lg.module.login.model.google.GoogleLoginDto;
import com.kr.lg.module.login.model.google.GoogleLoginRequestDto;

public interface GoogleService {

    GoogleLoginDto googleOAuth(GoogleLoginRequestDto requestDto) throws Exception;
}
