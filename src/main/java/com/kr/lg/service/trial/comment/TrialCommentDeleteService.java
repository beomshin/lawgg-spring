package com.kr.lg.service.trial.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.web.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialCommentDeleteService {

    @Transactional
    void deleteCommentTrial(TrialLayer requestDto) throws LgException;
}
