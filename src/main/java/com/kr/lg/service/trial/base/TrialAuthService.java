package com.kr.lg.service.trial.base;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialAuthService {

    void loginUserTrial(TrialLayer requestDto) throws LgException;
}
