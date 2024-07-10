package com.kr.lg.db.query;

import com.kr.lg.enums.DepthEnum;
import com.kr.lg.enums.StatusEnum;
import com.kr.lg.db.builder.TrialBuilder;
import com.kr.lg.model.querydsl.QTrialQ;
import com.kr.lg.model.querydsl.TrialQ;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.kr.lg.model.common.layer.TrialLayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.kr.lg.db.entities.QTrialAttachTb.trialAttachTb;
import static com.kr.lg.db.entities.QTrialCommentTb.trialCommentTb;
import static com.kr.lg.db.entities.QTrialTb.trialTb;
import static com.kr.lg.db.entities.QUserTb.userTb;


@Repository
@RequiredArgsConstructor
@Slf4j
public class TrialQuery {

    private final JPAQueryFactory jpaQueryFactory;
    private final TrialBuilder trialBuilder;


    public JPAQuery<TrialQ> findAllListTrial(TrialLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QTrialQ(
                        trialTb.trialId,
                        trialTb.title,
                        trialTb.content,
                        trialTb.writer,
                        userTb.profile,
                        trialTb.thumbnail,
                        trialTb.view,
                        trialTb.recommendCount,
                        trialTb.commentCount,
                        trialTb.writeDt,
                        trialTb.postType,
                        trialTb.liveType
                        )
                )
                .from(trialTb)
                .leftJoin(userTb).on(trialTb.userTb.userId.eq(userTb.userId))
                .where(
                        trialBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword()),
                        trialTb.status.eq(StatusEnum.NORMAL_STATUS)
                )
                .orderBy(trialBuilder.orderTopic(requestDto.getTopic()).stream().toArray(OrderSpecifier[]::new)) // 정렬조건
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(trialTb.trialId);
    }

    public JPAQuery<Long> findAllListTrialCount(TrialLayer requestDto) {
        return jpaQueryFactory
                .select(trialTb.count())
                .from(trialTb)
                .where(
                        trialBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword()),
                        trialTb.status.eq(StatusEnum.NORMAL_STATUS)
                );
    }

    public JPAQuery<TrialQ> findUserListTrial(TrialLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(
                        new QTrialQ(
                                trialTb.trialId,
                                trialTb.title,
                                trialTb.content,
                                trialTb.writer,
                                userTb.profile,
                                trialTb.thumbnail,
                                trialTb.view,
                                trialTb.recommendCount,
                                trialTb.commentCount,
                                trialTb.writeDt,
                                trialTb.postType,
                                trialTb.liveType
                        )
                )
                .from(trialTb)
                .where(
                        trialBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword()),
                        trialTb.status.eq(StatusEnum.NORMAL_STATUS),
                        trialTb.userTb.userId.eq(requestDto.getUserTb().getUserId())
                )
                .orderBy(trialBuilder.orderTopic(requestDto.getTopic()).stream().toArray(OrderSpecifier[]::new)) // 정렬조건
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(trialTb.trialId);
    }

    public JPAQuery<Long> findUserListTrialCount(TrialLayer requestDto) {
        return jpaQueryFactory
                .select(trialTb.count())
                .from(trialTb)
                .where(
                        trialBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword()),
                        trialTb.status.eq(StatusEnum.NORMAL_STATUS),
                        trialTb.userTb.userId.eq(requestDto.getUserTb().getUserId())
                );
    }

    public JPAQuery<TrialQ> findDetailTrial(TrialLayer requestDto) {
        return jpaQueryFactory
                .select(new QTrialQ(
                        trialTb.trialId,
                        trialTb.judge.userId,
                        trialTb.title,
                        trialTb.writer,
                        trialTb.writeDt,
                        trialTb.view,
                        trialTb.recommendCount,
                        trialTb.commentCount,
                        trialTb.plaintiff,
                        trialTb.defendant,
                        Expressions.as(this.getJudgeName(), "judgeName"),
                        trialTb.content,
                        trialTb.thumbnail,
                        trialTb.playVideo,
                        trialTb.plaintiffOpinion,
                        trialTb.defendantOpinion,
                        trialTb.url,
                        trialTb.precedent,
                        trialTb.liveType,
                        trialTb.endingType,
                        trialTb.status,
                        Expressions.as(this.getTrialCommentId(), "trialCommentId"),
                        trialTb.liveDt,
                        trialTb.replay,
                        trialTb.postType
                ))
                .from(trialTb)
                .where(
                        trialTb.trialId.eq(requestDto.getId())
                )
                .groupBy(trialTb.trialId);
    }

    public JPAQuery<String> getJudgeName() {
        return jpaQueryFactory.select(userTb.nickName)
                .from(userTb)
                .where(trialTb.judge.userId.eq(userTb.userId));
    }

    public JPAQuery<Long> getTrialCommentId() {
        return jpaQueryFactory.select(trialCommentTb.trialCommentId)
                .from(trialCommentTb)
                .where(trialTb.trialId.eq(trialCommentTb.trialTb.trialId),
                        trialCommentTb.depth.eq(DepthEnum.ROOT_COMMENT));
    }


    public JPAQuery<TrialQ> findLawFirmListTrial(TrialLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QTrialQ(
                                trialTb.trialId,
                                trialTb.title,
                                trialTb.content,
                                trialTb.writer,
                                userTb.profile,
                                trialTb.thumbnail,
                                trialTb.view,
                                trialTb.recommendCount,
                                trialTb.commentCount,
                                trialTb.writeDt,
                                trialTb.postType,
                                trialTb.liveType
                        )
                )
                .from(trialTb)
                .leftJoin(userTb).on(trialTb.userTb.userId.eq(userTb.userId))
                .where(
                        trialBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword()),
                        trialTb.status.eq(StatusEnum.NORMAL_STATUS),
                        trialTb.lawFirmTb.lawFirmId.eq(requestDto.getId())
                )
                .orderBy(trialBuilder.orderTopic(requestDto.getTopic()).stream().toArray(OrderSpecifier[]::new)) // 정렬조건
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(trialTb.trialId);
    }

    public JPAQuery<Long> findLawFirmListTrialCount(TrialLayer requestDto) {
        return jpaQueryFactory
                .select(trialTb.count())
                .from(trialTb)
                .where(
                        trialBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword()),
                        trialTb.status.eq(StatusEnum.NORMAL_STATUS),
                        trialTb.lawFirmTb.lawFirmId.eq(requestDto.getId())
                );
    }

    public JPAQuery<TrialQ> findTrialFiles(TrialLayer TrialLayer) {
        return jpaQueryFactory.select(
                        new QTrialQ(
                                trialAttachTb.trialAttachId,
                                trialAttachTb.path,
                                trialAttachTb.oriName,
                                trialAttachTb.newName,
                                trialAttachTb.size
                        )
                )
                .from(trialAttachTb)
                .where(
                        trialAttachTb.trialId.trialId.eq(TrialLayer.getId()),
                        trialAttachTb.status.eq(StatusEnum.NORMAL_STATUS)
                );
    }
}
