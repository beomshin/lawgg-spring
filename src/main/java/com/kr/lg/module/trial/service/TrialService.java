package com.kr.lg.module.trial.service;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.trial.model.req.DeleteTrialRequest;
import com.kr.lg.module.trial.model.req.VoteTrialRequest;
import com.kr.lg.module.trial.model.req.ReportTrialRequest;
import com.kr.lg.module.trial.model.req.*;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.model.entry.TrialEntry;
import org.springframework.data.domain.Page;

public interface TrialService {
    Page<TrialEntry> findTrials(FindTrialsRequest request) throws TrialException;
    Page<TrialEntry> findLawFirmTrials(FindLawFirmTrialsRequest request) throws TrialException;
    TrialEntry findTrialWithNotLogin(long id) throws TrialException;
    TrialEntry findTrialWithLogin(long id, UserTb userTb) throws TrialException;
    TrialTb enrollTrialWithLogin(EnrollTrialRequest request, UserTb userTb) throws TrialException;
    void trialStartLive(StartTrialRequest request, UserTb userTb) throws TrialException;
    void trialEndLive(EndTrialRequest request, UserTb userTb) throws TrialException;
    void recommendTrial(RecommendTrialRequest request, UserTb userTb) throws TrialException;
    void reportTrial(ReportTrialRequest request, String ip) throws TrialException;
    void voteTrial(VoteTrialRequest request, UserTb userTb) throws TrialException;
    void deleteTrial(DeleteTrialRequest request, UserTb userTb) throws TrialException;
}
