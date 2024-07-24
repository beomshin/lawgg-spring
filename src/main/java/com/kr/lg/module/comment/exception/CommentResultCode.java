package com.kr.lg.module.comment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommentResultCode {

    FAIL_ENROLL_COMMENT("4000", "댓글 등록 실패."),


    ;

    private final String code;
    private final String msg;
}
