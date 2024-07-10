package com.kr.lg.service.trial.comment;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialCommentUpdateService {

    @Transactional
    int updateCommentTrial(TrialLayer requestDto) throws LgException;
}
