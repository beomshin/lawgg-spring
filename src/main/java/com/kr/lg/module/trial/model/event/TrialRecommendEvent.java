package com.kr.lg.module.trial.model.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrialRecommendEvent {

    private Long trialId;
    private int num;
}
