package com.kr.lg.web.common.root;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.web.common.global.GlobalCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractSpec {

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final Date time = new Date(); // 날짜
    public String resultCode;
    public String resultMsg;
    public Boolean success;

    protected abstract void setError(GlobalCode code);

    protected AbstractSpec(GlobalCode code) {
        this.resultCode = code.getCode();
        this.resultMsg = code.getMsg();
        this.success = GlobalCode.isSuccess(code);
    }

}
