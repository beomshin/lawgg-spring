package com.kr.lg.service.trial.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.web.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialRecommendService {

    @Transactional
    void recommendTrial(TrialLayer requestDto) throws LgException;
    @Transactional
    int deleteRecommendTrial(TrialLayer requestDto) throws LgException;
}
