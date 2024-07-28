package com.kr.lg.module.trial.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class TrialCountEvent {

    private Long trialId;
    private String ip;
}
