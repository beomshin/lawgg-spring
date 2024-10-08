package com.kr.lg.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GlobalResultCode {

    SUCCESS("00000", "성공"),
    SYSTEM_ERROR("1001", "미지정 오류"),
    PARAMETER_ERROR("10002", "파라미터 오류"),
    ACCESS_DENIED_ERROR("10003", "미인증 계정"),
    ;

    private final String code;
    private final String msg;

}
