package com.kr.lg.module.trial.service;

import com.kr.lg.module.trial.exception.TrialException;

public interface TrialDeleteService {
    void deleteTrial(long trialId) throws TrialException;
}
