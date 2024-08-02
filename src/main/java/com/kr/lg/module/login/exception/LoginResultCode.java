package com.kr.lg.module.login.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LoginResultCode {

    FAIL_GOOGLE_CALLBACK_LOGIN("2000", "구글 콜백 로그인 실패"),
    FAIL_KAKAO_CALLBACK_LOGIN("2001", "카카오 콜백 로그인 실패"),
    FAIL_NAVER_CALLBACK_LOGIN("2002", "네이버 콜백 로그인 실패"),

    ;

    private final String code;
    private final String msg;
}
