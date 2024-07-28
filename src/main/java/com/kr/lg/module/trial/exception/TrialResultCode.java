package com.kr.lg.module.trial.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrialResultCode {

    FAIL_FIND_TRIAL("6000", "트라이얼 조회 실패."),
    NOT_EXIST_TRIAL("6001", "트라이얼 미존재"),
    FAIL_FIND_TRAIL_VOTE("6001", "트라이얼 투표 정보 조회 실패"),
    FAIL_ENROLL_TRAIL("6002", "트라이얼 등록 실패"),
    FAIL_ENROLL_TRAIL_IMAGE("6003", "트라이얼 이미지 등록 실패"),
    FAIL_UPDATE_LIVE_START_TRIAL("6004", "트라이얼 재판 시작 실패"),
    FAIL_UPDATE_LIVE_END_TRIAL("6005", "트라이얼 재판 종료 실패"),
    FAIL_RECOMMEND_TRIAL("6006", "트라이얼 추천 실패"),
    FAIL_DELETE_RECOMMEND_TRIAL("6007", "트라이얼 추천 취소 실패"),
    ALREADY_RECOMMEND_TRIAL("6008", "중복 추천 방지"),
    ALREADY_DELETE_RECOMMEND_TRIAL("6009", "추천 내역 미존재로인한 취소 실패"),
    FAIL_REPORT_TRIAL("6010", "트라이얼 신고 실패"),
    FAIL_VOTE_TRIAL("6011", "트라이얼 투표 실패"),
    FAIL_CHANGE_VOTE_TRIAL("6012", "트라이얼 투표 변경 실패"),
    UN_MATCH_PASSWORD("6013", "비밀번호 불일치"),
    FAIL_DELETE_TRIAL("6014", "트라이얼 삭제 실패"),
    ;

    private final String code;
    private final String msg;
}
