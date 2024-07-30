package com.kr.lg.module.user.excpetion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserResultCode {

    FAIL_FIND_USER_BOARDS("8001", "유저 게시판 조회 실패"),
    FAIL_CERTIFICATION("8002", "본인 인증을 실패"),
    NOT_EXIST_ENROLL_ID("8003", "회원가입 아이디가 미존재"),
    UN_MATCH_PASSWORD("8004", "비밀번호 불일치"),
    FAIL_FIND_USER("8005", "유저 조회 실패"),
    FAIL_FIND_USER_ALERTS("8006", "유저 게시판 조회 실패"),

    ;

    private final String code;
    private final String msg;
}
