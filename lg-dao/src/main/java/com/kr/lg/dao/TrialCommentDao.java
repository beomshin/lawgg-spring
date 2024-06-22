package com.kr.lg.dao;

import com.kr.lg.model.web.querydsl.TrialQ;
import com.kr.lg.model.web.common.layer.TrialLayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrialCommentDao {

    Long findRootComment(Long trialId);
    Page<TrialQ> findAnonymousCommentTrial(TrialLayer requestDto, Pageable pageable);
    Page<TrialQ> findUserCommentTrial(TrialLayer requestDto, Pageable pageable);
}
