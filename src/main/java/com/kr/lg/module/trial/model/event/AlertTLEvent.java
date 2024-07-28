package com.kr.lg.module.trial.model.event;

import com.kr.lg.db.entities.TrialTb;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Getter
public class AlertTLEvent { // AlertTrialLiveEvent

    private TrialTb trialTb;


}
