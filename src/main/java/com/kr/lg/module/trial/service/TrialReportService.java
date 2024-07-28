package com.kr.lg.module.trial.service;

import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.model.dto.TrialReportDto;

public interface TrialReportService {
    void reportTrial(TrialReportDto reportDto) throws TrialException;
}
