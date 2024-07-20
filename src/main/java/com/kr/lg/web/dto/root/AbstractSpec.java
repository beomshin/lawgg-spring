package com.kr.lg.web.dto.root;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.web.dto.global.GlobalCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractSpec {

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final Date time = new Date(); // 날짜
    public String resultCode = GlobalCode.SUCCESS.getCode(); // 응답 코드
    public String resultMsg = GlobalCode.SUCCESS.getMsg();; // 응답 메세지
    public boolean success = true; // API 성공 여부

    public AbstractSpec(String code, String msg, boolean success) {
        this.resultMsg = code;
        this.resultCode = msg;
        this.success = success;
    }

}