package com.kr.lg.module.lawfirm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmResultCode {

    ALREADY_JOIN_USER("5000", "로펌 가입 유저"),
    ALREADY_APPLY_USER("5001", "로펌 신청 유저"),
    FAIL_APPLY_LAW_FIRM("5002", "로펌 신청 실패"),
    FAIL_QUIT_LAW_FIRM("5003", "로펌 탈퇴에 실패했습니다."),
    FAIL_FIND_LAW_FIRMS("5004", "로펌 리스트 조회 실패."),
    FAIL_FIND_LAW_FIRM("5005", "로펌 조회 실패."),
    FAIL_FIND_LAW_FIRMS_BOARD("5006", "로펌 게시판 리스트 조회 실패."),
    ;

    private final String code;
    private final String msg;
}
