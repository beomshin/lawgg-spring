package com.kr.lg.service.trial.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialCommentFindService {
    DefaultResponse findAnonymousAllCommentTrial(TrialLayer requestDto) throws LgException;
    DefaultResponse findUserAllCommentTrial(TrialLayer requestDto) throws LgException;
    DefaultResponse findAnonymousParentCommentTrial(TrialLayer requestDto) throws LgException;
    DefaultResponse findUserParentCommentTrial(TrialLayer requestDto) throws LgException;
    DefaultResponse findAnonymousChildrenCommentTrial(TrialLayer requestDto) throws LgException;
    DefaultResponse findUserChildrenCommentTrial(TrialLayer requestDto) throws LgException;
}
