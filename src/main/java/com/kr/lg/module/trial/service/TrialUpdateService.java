package com.kr.lg.module.trial.service;

import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.model.dto.TrialUpdateDto;

public interface TrialUpdateService {
    void updateLiveStartTrial(TrialUpdateDto updateDto) throws TrialException;
    void updateEndTrial(TrialUpdateDto updateDto) throws TrialException;
}
