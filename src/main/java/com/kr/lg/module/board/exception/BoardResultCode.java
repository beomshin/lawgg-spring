package com.kr.lg.module.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardResultCode {

    FAIL_FIND_BOARD("3000", "게시판 조회를 실패하였습니다."),
    NOT_EXIST_BOARD("3001", "게시판 미존재"),
    DELETE_BOARD("3002", "삭제 게시판"),
    REPORT_BOARD("3003", "중지 게시판"),
    ;

    private final String code;
    private final String msg;
}
