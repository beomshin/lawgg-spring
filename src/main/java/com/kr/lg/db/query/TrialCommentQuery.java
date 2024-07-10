package com.kr.lg.db.query;

import com.kr.lg.enums.DepthEnum;
import com.kr.lg.enums.StatusEnum;
import com.kr.lg.model.querydsl.QTrialQ;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.kr.lg.model.querydsl.TrialQ;
import com.kr.lg.model.common.layer.TrialLayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import static com.kr.lg.db.entities.QTrialCommentTb.trialCommentTb;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TrialCommentQuery {

    private final JPAQueryFactory jpaQueryFactory;

    public JPAQuery<Long> findRootComment(Long trialId) {
        return jpaQueryFactory
                .select(trialCommentTb.trialCommentId)
                .from(trialCommentTb)
                .where(
                        trialCommentTb.trialTb.trialId.eq(trialId),
                        trialCommentTb.depth.eq(DepthEnum.ROOT_COMMENT)
                );
    }

    public JPAQuery<TrialQ> findAnonymousCommentTrial(TrialLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QTrialQ(
                        trialCommentTb.trialCommentId,
                        trialCommentTb.parentId,
                        trialCommentTb.order,
                        trialCommentTb.depth,
                        trialCommentTb.writer,
                        trialCommentTb.content,
                        trialCommentTb.status,
                        trialCommentTb.regDt,
                        new CaseBuilder().when(trialCommentTb.userTb.isNull()).then(1).otherwise(0).as("anonym")
                ))
                .from(trialCommentTb)
                .where(
                        trialCommentTb.trialTb.trialId.eq(requestDto.getId()),
                        trialCommentTb.depth.eq(requestDto.getDepth()),
                        trialCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(trialCommentTb.trialCommentId.asc());
    }

    public JPAQuery<Long> findAnonymousCommentTrialCount(TrialLayer requestDto) {
        return jpaQueryFactory
                .select(trialCommentTb.count())
                .from(trialCommentTb)
                .where(
                        trialCommentTb.trialTb.trialId.eq(requestDto.getId()),
                        trialCommentTb.depth.eq(requestDto.getDepth()),
                        trialCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                );
    }

    public JPAQuery<TrialQ> findUserCommentTrial(TrialLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QTrialQ(
                        trialCommentTb.trialCommentId,
                        trialCommentTb.parentId,
                        trialCommentTb.order,
                        trialCommentTb.depth,
                        trialCommentTb.writer,
                        trialCommentTb.content,
                        trialCommentTb.status,
                        trialCommentTb.regDt,
                        new CaseBuilder().when(trialCommentTb.userTb.userId.eq(requestDto.getUserTb().getUserId())).then(1).otherwise(0).as("created"),
                        new CaseBuilder().when(trialCommentTb.userTb.isNull()).then(1).otherwise(0).as("anonym")
                ))
                .from(trialCommentTb)
                .where(
                        trialCommentTb.trialTb.trialId.eq(requestDto.getId()),
                        trialCommentTb.depth.eq(requestDto.getDepth()),
                        trialCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(trialCommentTb.trialCommentId.asc());
    }

    public JPAQuery<Long> findUserCommentTrialCount(TrialLayer requestDto) {
        return jpaQueryFactory
                .select(trialCommentTb.count())
                .from(trialCommentTb)
                .where(
                        trialCommentTb.trialTb.trialId.eq(requestDto.getId()),
                        trialCommentTb.depth.eq(requestDto.getDepth()),
                        trialCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                );
    }

}