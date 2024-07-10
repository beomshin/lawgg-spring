package com.kr.lg.db.dao.impl;

import com.kr.lg.model.querydsl.TrialQ;
import com.kr.lg.model.common.layer.TrialLayer;
import com.kr.lg.db.dao.TrialCommentDao;
import com.kr.lg.db.query.TrialCommentQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TrialCommentDaoImpl implements TrialCommentDao {

    private final TrialCommentQuery trialCommentQuery;

    @Override
    public Long findRootComment(Long trialId) {
        return trialCommentQuery.findRootComment(trialId).fetchOne(); // 루트 댓글 식별자 조회
    }

    @Override
    public Page<TrialQ> findAnonymousCommentTrial(TrialLayer requestDto, Pageable pageable) {
        JPAQuery<TrialQ> content = trialCommentQuery.findAnonymousCommentTrial(requestDto, pageable);
        JPAQuery<Long> count = trialCommentQuery.findAnonymousCommentTrialCount(requestDto);
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Page<TrialQ> findUserCommentTrial(TrialLayer requestDto, Pageable pageable) {
        JPAQuery<TrialQ> content = trialCommentQuery.findUserCommentTrial(requestDto, pageable);
        JPAQuery<Long> count = trialCommentQuery.findUserCommentTrialCount(requestDto);
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }
}
