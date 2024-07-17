package com.kr.lg.web.common.root;

import com.kr.lg.module.auth.excpetion.AuthResultCode;
import com.kr.lg.web.common.global.GlobalCode;


public class ErrorResponse extends AbstractSpec {

    public ErrorResponse(GlobalCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    public ErrorResponse(AuthResultCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }



    private static boolean isSuccess(String code) {
        return GlobalCode.SUCCESS.getCode().equals(code);
    }

}
