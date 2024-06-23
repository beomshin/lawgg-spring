package com.kr.lg.service.trial.base;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialVoteService {

    @Transactional
    void voteTrial(TrialLayer requestDto) throws LgException;
}
