package com.kr.lg.module.message.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageResultCode {

    FAIL_FIND_MESSAGES("7000", "메세지 리스트 조회 실패."),
    FAIL_SEND_MESSAGES("7001", "메세지 전송 실패."),
    NOT_EXIST_MEMBER("7002", "미존재 유저"),
    NOT_SEND_SELF("7003", "자신 메일 발송 제한"),

    ;

    private final String code;
    private final String msg;
}
