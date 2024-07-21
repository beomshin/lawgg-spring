package com.kr.lg.module.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardResultCode {

    FAIL_FIND_BOARD("3000", "게시판 조회를 실패하였습니다.")
    ;

    private final String code;
    private final String msg;
}
