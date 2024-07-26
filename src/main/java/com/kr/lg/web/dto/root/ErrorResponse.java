package com.kr.lg.web.dto.root;

import com.kr.lg.module.auth.excpetion.AuthResultCode;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.comment.exception.CommentResultCode;
import com.kr.lg.module.lawfirm.exception.LawFirmResultCode;
import com.kr.lg.web.dto.global.GlobalCode;


public class ErrorResponse extends AbstractSpec {

    public ErrorResponse(String code, String msg) {
        super(code, msg, isSuccess(code));
    }

    public ErrorResponse(GlobalCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    public ErrorResponse(AuthResultCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    public ErrorResponse(BoardResultCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    public ErrorResponse(CommentResultCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    public ErrorResponse(LawFirmResultCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    private static boolean isSuccess(String code) {
        return GlobalCode.SUCCESS.getCode().equals(code);
    }

}
