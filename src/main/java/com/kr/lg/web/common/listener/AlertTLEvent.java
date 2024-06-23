package com.kr.lg.web.common.listener;

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
