package com.kr.lg.module.trial.service;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.model.dto.TrialEnrollDto;

import java.util.List;

public interface TrialEnrollService {

    TrialTb enrollTrial(TrialEnrollDto enrollDto) throws TrialException;
    <T> void enrollTrialFiles(TrialTb trialTb, List<T> files) throws TrialException;

}
