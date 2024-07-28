package com.kr.lg.module.message.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageResultCode {

    FAIL_FIND_MESSAGES("7000", "메세지 리스트 조회 실패."),

    ;

    private final String code;
    private final String msg;
}
