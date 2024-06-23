package com.kr.lg.service.trial.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialCommentEnrollService {

    @Transactional
    void enrollCommentTrial(TrialLayer requestDto) throws LgException;
}
