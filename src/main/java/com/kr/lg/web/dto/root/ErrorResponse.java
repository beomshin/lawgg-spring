package com.kr.lg.web.dto.root;

import com.kr.lg.module.auth.excpetion.AuthResultCode;
import com.kr.lg.web.dto.global.GlobalCode;


public class ErrorResponse extends AbstractSpec {

    public ErrorResponse(GlobalCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    public ErrorResponse(AuthResultCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    public ErrorResponse(String code, String msg) {
        super(code, msg, isSuccess(code));
    }

    private static boolean isSuccess(String code) {
        return GlobalCode.SUCCESS.getCode().equals(code);
    }

}
