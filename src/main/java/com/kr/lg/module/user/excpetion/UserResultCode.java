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
    NOT_EXIST_USER("8007", "미존재 유저"),
    FAIL_UPDATE_USER_PASSWORD("8008", "유저 패스워드 업데이트 실패"),
    OVERLAP_LOGIN_ID("8009", "이미 사용중인 아이디입니다."),
    FAIL_UPDATE_USER_INFO("8009", "유저 정보 업데이트 실패"),
    FAIL_FILE_UPLOAD("8010", "파일 업로드에 실패"),
    FAIL_UPDATE_USER_PROFILE("8011", "유저 프로필 업데이트 실패"),
    FAIL_UPDATE_USER_ALERT("8012", "유저 알림 업데이트 실패"),
    FAIL_UPDATE_USER_ALERT_LIST("8013", "유저 알림 리스트 업데이트 실패"),
    NOT_EXIST_ALERT("8014", "미존재 유저 알림"),
    FAIL_ENROLL_USER("8015", "유저 등록 실패"),
    ALREADY_ENROLL_USER("8016", "이미 등록된 유저"),

    ;

    private final String code;
    private final String msg;
}
