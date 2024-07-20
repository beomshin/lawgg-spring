package com.kr.lg.module.auth.excpetion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthResultCode {

    NOT_EXIST_JWT_TOKEN("2000", "토큰 누락"),
    FAIL_VALIDATE_JWT_TOKEN("2001", "토큰 유효성 검사 실패"),
    FAIL_FIND_USER_INFO("2002", "유저 정보 조회 실패"),
    FAIL_FIND_TOKEN_INFO("2003", "토큰 정보 조회 실패"),
    UN_MATCHED_PASSWORD("2004", "패스워드 불일치"),
    NOT_EXIST_USER("2005", "미존재 아이디"),
    LOCK_LOGIN_ID("2006", "정지 아이디."),
    DELETE_LOGIN_ID("2007", "삭제 아이디"),
    FAIL_LOGIN("2008", "로그인 실패(사유 확인 필요)"),

    ;

    private final String code;
    private final String msg;
}
