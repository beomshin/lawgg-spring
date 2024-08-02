package com.kr.lg.model.global;

import com.kr.lg.model.enums.GlobalCode;
import com.kr.lg.module.auth.excpetion.AuthResultCode;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.comment.exception.CommentResultCode;
import com.kr.lg.module.lawfirm.exception.LawFirmResultCode;
import com.kr.lg.module.message.exception.MessageResultCode;
import com.kr.lg.module.thirdparty.exception.ThirdPartyResultCode;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.module.user.excpetion.UserResultCode;


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

    public ErrorResponse(TrialResultCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    public ErrorResponse(MessageResultCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    public ErrorResponse(UserResultCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }

    public ErrorResponse(ThirdPartyResultCode code) {
        super(code.getCode(), code.getMsg(), isSuccess(code.getCode()));
    }
    private static boolean isSuccess(String code) {
        return GlobalCode.SUCCESS.getCode().equals(code);
    }

}
