package com.kr.lg.module.lawfirm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LawFirmResultCode {

    ALREADY_JOIN_USER("5000", "로펌 가입 유저"),
    ALREADY_APPLY_USER("5001", "로펌 신청 유저"),
    FAIL_APPLY_LAW_FIRM("5002", "로펌 신청 실패"),

    ;

    private final String code;
    private final String msg;
}
