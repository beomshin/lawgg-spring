package com.kr.lg.module.auth.excpetion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthResultCode {

    FAIL_VALIDATE_JWT_TOKEN("2001", "토큰 유효성 검사 실패"),
    FAIL_FIND_USER_INFO("2002", "유저 정보 조회 실패"),
    FAIL_FIND_TOKEN_INFO("2003", "토큰 정보 조회 실패")

    ;

    private final String code;
    private final String msg;
}
