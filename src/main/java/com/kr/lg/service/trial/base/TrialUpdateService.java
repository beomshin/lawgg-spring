package com.kr.lg.service.trial.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.web.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialUpdateService {

    @Transactional
    void updateTrial(TrialLayer requestDto) throws LgException;

    @Transactional
    void updateLiveTrial(TrialLayer requestDto) throws LgException;

    @Transactional
    void updateEndTrial(TrialLayer requestDto) throws LgException;
}
