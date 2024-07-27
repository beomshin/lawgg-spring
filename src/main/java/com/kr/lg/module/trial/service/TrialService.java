package com.kr.lg.module.trial.service;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.trial.model.req.*;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.model.entry.TrialEntry;
import org.springframework.data.domain.Page;

public interface TrialService {
    Page<TrialEntry> findTrials(FindTrialsRequest request) throws TrialException;
    Page<TrialEntry> findLawFirmTrials(FindLawFirmTrialsRequest request) throws TrialException;
    TrialEntry findTrialWithNotLogin(long id) throws TrialException;
    TrialEntry findTrialWithLogin(long id, UserTb userTb) throws TrialException;
    TrialTb enrollTrialWithLogin(EnrollTrialWithLoginRequest request, UserTb userTb) throws TrialException;
    TrialTb enrollVideoWithLogin(EnrollVideoWithLoginRequest request, UserTb userTb) throws TrialException;
    TrialTb trialStartLive(UpdateLiveTrialRequest request, UserTb userTb) throws TrialException;
    void trialEndLive(UpdateEndTrialRequest request, UserTb userTb) throws TrialException;
}
