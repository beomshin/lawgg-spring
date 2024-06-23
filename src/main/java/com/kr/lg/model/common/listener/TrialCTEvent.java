package com.kr.lg.model.common.listener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class TrialCTEvent { // TrialCreateEvent

    private Long trialId;
    private String ip;
}
