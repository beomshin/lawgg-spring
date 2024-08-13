package com.kr.lg.module.comment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommentResultCode {

    FAIL_ENROLL_COMMENT("4000", "댓글 등록 실패."),
    FAIL_UPDATE_COMMENT("4001", "댓글 수정 실패."),
    UN_MATCH_PASSWORD("4002", "댓글 비밀번호가 불일치"),
    UN_MATCHED_USER("4003", "댓글 미매칭 유저 수정"),
    FAIL_REPORT_COMMENT("4004", "댓글 신고 실패"),
    FAIL_DELETE_COMMENT("4005", "댓글 삭제 실패"),
    FAIL_FIND_BOARD_COMMENT("4006", "게시판 댓글 조회 실패."),
    ALREADY_DELETE_BOARD_COMMENT("4007", "이미 삭제된 게시판 댓글"),
    FAIL_FIND_TRIAL_COMMENT("4008", "트라이얼 댓글 조회 실패."),
    ALREADY_DELETE_TRIAL_COMMENT("4009", "이미 삭제된 트라이얼 댓글"),
    FAIL_FIND_BOARD("4010", "포지션 게시판 조회 실패"),
    ;

    private final String code;
    private final String msg;
}
