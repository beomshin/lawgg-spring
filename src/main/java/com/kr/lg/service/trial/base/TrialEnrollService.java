package com.kr.lg.service.trial.base;

import com.kr.lg.db.entities.TrialTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialEnrollService {

    @Transactional
    TrialTb enrollUserTrial(TrialLayer requestDto) throws LgException;
    @Transactional
    TrialTb enrollLawFirmTrial(TrialLayer requestDto) throws LgException;
    @Transactional
    void enrollVideo(TrialLayer requestDto) throws LgException;
}
