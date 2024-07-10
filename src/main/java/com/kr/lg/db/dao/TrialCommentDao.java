package com.kr.lg.db.dao;

import com.kr.lg.model.querydsl.TrialQ;
import com.kr.lg.model.common.layer.TrialLayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrialCommentDao {

    Long findRootComment(Long trialId);
    Page<TrialQ> findAnonymousCommentTrial(TrialLayer requestDto, Pageable pageable);
    Page<TrialQ> findUserCommentTrial(TrialLayer requestDto, Pageable pageable);
}
