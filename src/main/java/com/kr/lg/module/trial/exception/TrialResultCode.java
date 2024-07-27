package com.kr.lg.module.trial.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TrialResultCode {

    FAIL_FIND_TRIAL("6000", "트라이얼 조회 실패."),
    NOT_EXIST_TRIAL("60001", "트라이얼 미존재"),
    FAIL_FIND_TRAIL_VOTE("60001", "트라이얼 투표 정보 조회 실패"),


    ;

    private final String code;
    private final String msg;
}
