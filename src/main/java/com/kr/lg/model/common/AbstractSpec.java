package com.kr.lg.model.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.model.enums.GlobalResultCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractSpec {

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final Date time = new Date(); // 날짜
    public String resultCode = GlobalResultCode.SUCCESS.getCode(); // 응답 코드
    public String resultMsg = GlobalResultCode.SUCCESS.getMsg();; // 응답 메세지
    public boolean success = true; // API 성공 여부

    public AbstractSpec(String code, String msg, boolean success) {
        this.resultCode = code;
        this.resultMsg = msg;
        this.success = success;
    }

}
