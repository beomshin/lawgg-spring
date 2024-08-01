package com.kr.lg.module.thirdparty.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ThirdPartyResultCode {

    FAIL_INSERT_MAIL("9000","메일 내역 저장 실패"),
    FAIL_VERIFY_EMAIL("9001", "이메일 인증 실패"),
    FAIL_VERIFY_MAIL("9002","메일 인증 내역 저장 실패"),
    FAIL_CERTIFICATION("9003", "본인 인증을 실패"),
    FAIL_INSERT_VERIFY_USER_DATA("9004", "유저 인증 데이터 적재 실패"),

    ;

    private final String code;
    private final String msg;
}
