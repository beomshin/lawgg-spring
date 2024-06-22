package com.kr.lg.service.trial.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialCommentReportService {

    @Transactional
    int reportCommentTrial(TrialLayer requestDto) throws LgException;
}
