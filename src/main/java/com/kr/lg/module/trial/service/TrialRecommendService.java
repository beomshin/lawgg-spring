package com.kr.lg.module.trial.service;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.trial.exception.TrialException;

public interface TrialRecommendService {
    void recommendTrial(TrialTb trialTb, UserTb userTb) throws TrialException;
}
