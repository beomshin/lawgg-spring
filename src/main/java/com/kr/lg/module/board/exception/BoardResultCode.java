package com.kr.lg.module.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BoardResultCode {

    FAIL_FIND_BOARD("3000", "게시판 조회 실패."),
    NOT_EXIST_BOARD("3001", "게시판 미존재"),
    DELETE_BOARD("3002", "삭제 게시판"),
    REPORT_BOARD("3003", "중지 게시판"),
    FAIL_ENROLL_BOARD("3004", "게시판 등록 실패"),
    NOT_EXIST_USER("3005", "로그인 필수"),
    FAIL_ENROLL_BOARD_IMAGE("3006", "게시판 이미지 등록 실패"),
    NOT_EXIST_LAW_FIRM("3007", "로펌 미가입자"),
    UN_MATCHED_LAW_FIRM_USER("3008", "미매칭 로펌 등록"),
    UN_MATCH_PASSWORD("3009", "비밀번호가 불일치"),
    UN_MATCHED_USER("3010", "미매칭 유저 수정"),
    FAIL_UPDATE_BOARD("3011", "게시판 수정 실패"),
    FAIL_DELETE_BOARD("3012", "게시판 삭제 실패"),
    FAIL_REPORT_BOARD("3013", "게시판 신고 실패"),
    ;

    private final String code;
    private final String msg;
}
