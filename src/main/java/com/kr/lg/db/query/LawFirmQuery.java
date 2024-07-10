package com.kr.lg.db.query;

import com.kr.lg.db.builder.LawFirmBuilder;
import com.kr.lg.enums.*;
import com.kr.lg.model.querydsl.QLawFirmQ;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.kr.lg.model.common.layer.LawFLayer;
import com.kr.lg.model.querydsl.LawFirmQ;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.kr.lg.db.entities.QLawFirmTb.lawFirmTb;
import static com.kr.lg.db.entities.QLawFirmApplyTb.lawFirmApplyTb;
import static com.kr.lg.db.entities.QTrialTb.trialTb;
import static com.kr.lg.db.entities.QUserTb.userTb;

@Component
@RequiredArgsConstructor
@Slf4j
public class LawFirmQuery {

    private final JPAQueryFactory jpaQueryFactory;
    public final LawFirmBuilder lawFirmBuilder;

    public JPAQuery<LawFirmQ> findAllLawFirmList(LawFLayer LawFLayer, Pageable pageable) {
        return jpaQueryFactory.select(
                new QLawFirmQ(
                    lawFirmTb.lawFirmId,
                    lawFirmTb.name,
                    userTb.nickName,
                    lawFirmTb.profile,
                    lawFirmTb.background,
                    lawFirmTb.status,
                    userCount()
                ))
                .where(
                        lawFirmBuilder.eqKeyword(LawFLayer.getSubject(), "%" + LawFLayer.getKeyword() + "%"),
                        lawFirmTb.status.eq(Status2Enum.NORMAL_STATUS)
                )
                .from(lawFirmTb)
                .leftJoin(userTb).on(lawFirmTb.userTb.userId.eq(userTb.userId))
                .orderBy(lawFirmTb.lawFirmId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    public JPAQuery<Long> findAllLawFirmListCount(LawFLayer LawFLayer) { // 로펌 개수
        return jpaQueryFactory
                .select(lawFirmTb.count())
                .from(lawFirmTb)
                .leftJoin(userTb).on(lawFirmTb.userTb.userId.eq(userTb.userId))
                .where(
                        lawFirmBuilder.eqKeyword(LawFLayer.getSubject(), "%" + LawFLayer.getKeyword() + "%"),
                        lawFirmTb.status.eq(Status2Enum.NORMAL_STATUS)
                );
    }

    private JPAQuery<Long> userCount() {
        return jpaQueryFactory.select(userTb.count()).from(userTb)
                .where(
                        userTb.lawFirmId.lawFirmId.eq(lawFirmTb.lawFirmId),
                        userTb.status.eq(Status3Enum.NORMAL_STATUS)
                );
    }


    public JPAQuery<LawFirmQ> findLawFirmDetail(LawFLayer LawFLayer) {
        return jpaQueryFactory.select(
                        new QLawFirmQ(
                                lawFirmTb.lawFirmId,
                                lawFirmTb.name,
                                lawFirmTb.introduction,
                                userTb.nickName,
                                lawFirmTb.profile,
                                lawFirmTb.background,
                                Expressions.as(trialTotalCount(LawFLayer.getId()), "trialTotalCount"),
                                Expressions.as(trialCount(LawFLayer.getId()), "trialCount"),
                                Expressions.as(winRate(LawFLayer.getId()), "winRate"),
                                lawFirmTb.status
                        ))
                .from(lawFirmTb)
                .join(userTb).on(lawFirmTb.userTb.userId.eq(userTb.userId))
                .where(
                        lawFirmTb.lawFirmId.eq(LawFLayer.getId()),
                        lawFirmTb.status.eq(Status2Enum.NORMAL_STATUS)
                )
                .groupBy(lawFirmTb.lawFirmId);
    }

    private JPAQuery<Long> winRate(Long lawfirmId) { // 선고율 계산
        return jpaQueryFactory.select(trialTb.count().divide(trialTotalCount(lawfirmId)).multiply(100))
                .from(trialTb)
                .where(
                        trialTb.status.eq(StatusEnum.NORMAL_STATUS),
                        trialTb.lawFirmTb.lawFirmId.eq(lawfirmId),
                        trialTb.endingType.eq(EndingEnum.ENDING_TYPE),
                        trialTb.liveType.eq(LiveEnum.LIVE_TYPE)
                );
    }

    private JPAQuery<Long> trialCount(Long lawfirmId) { // 누적요청
        return jpaQueryFactory.select(trialTb.count()).from(trialTb)
                .where(
                        trialTb.status.eq(StatusEnum.NORMAL_STATUS),
                        trialTb.lawFirmTb.lawFirmId.eq(lawfirmId)
                );
    }

    private JPAQuery<Long> trialTotalCount(Long lawfirmId) { // 재판 개수
        return jpaQueryFactory.select(trialTb.count()).from(trialTb)
                .where(
                        trialTb.lawFirmTb.lawFirmId.eq(lawfirmId),
                        trialTb.status.eq(StatusEnum.NORMAL_STATUS),
                        trialTb.liveType.eq(LiveEnum.LIVE_TYPE)
                );
    }

    public JPAQuery<Long> findApply(LawFLayer LawFLayer) { // 로펌 지원하기
        return jpaQueryFactory.select(lawFirmApplyTb.count())
                .from(lawFirmApplyTb)
                .where(
                        lawFirmApplyTb.lawFirmTb.lawFirmId.eq(LawFLayer.getId()),
                        lawFirmApplyTb.userTb.userId.eq(LawFLayer.getUserTb().getUserId()),
                        lawFirmApplyTb.status.eq(ApplyStatusEnum.APPLY_STATUS)
                );
    }


    public JPAQuery<LawFirmQ> findMyLawFirm(Long lawfirmId) {
        return jpaQueryFactory.select(
                        new QLawFirmQ(
                                lawFirmTb.lawFirmId,
                                lawFirmTb.name,
                                userTb.nickName,
                                lawFirmTb.profile,
                                lawFirmTb.background,
                                Expressions.as(trialTotalCount(lawfirmId), "trialTotalCount"),
                                Expressions.as(trialCount(lawfirmId), "trialCount"),
                                Expressions.as(winRate(lawfirmId), "winRate"),
                                lawFirmTb.status,
                                lawFirmTb.regDt,
                                userCount()
                        ))
                .from(lawFirmTb)
                .join(userTb).on(lawFirmTb.userTb.userId.eq(userTb.userId))
                .where(
                        lawFirmTb.lawFirmId.eq(lawfirmId)
                )
                .groupBy(lawFirmTb.lawFirmId);
    }

    public JPAQuery<LawFirmQ> findApplyListMyLawFirm(LawFLayer LawFLayer, Pageable pageable) {
        return jpaQueryFactory.select(
                    new QLawFirmQ(
                            lawFirmApplyTb.lawFirmAppyId,
                            userTb.nickName,
                            userTb.profile,
                            lawFirmApplyTb.regDt,
                            userTb.status
                    )
                )
                .from(lawFirmApplyTb)
                .join(userTb).on(lawFirmApplyTb.userTb.userId.eq(userTb.userId))
                .where(
                        lawFirmApplyTb.lawFirmTb.lawFirmId.eq(LawFLayer.getId()),
                        lawFirmApplyTb.status.eq(ApplyStatusEnum.APPLY_STATUS),
                        lawFirmBuilder.eqKeywordMy(LawFLayer.getSubject(), "%" + LawFLayer.getKeyword() + "%")
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    public JPAQuery<Long> findApplyListMyLawFirmtCount(LawFLayer LawFLayer) { // 로펌 개수
        return jpaQueryFactory
                .select(lawFirmApplyTb.count())
                .where(
                        lawFirmApplyTb.lawFirmTb.lawFirmId.eq(LawFLayer.getId()),
                        lawFirmApplyTb.status.eq(ApplyStatusEnum.APPLY_STATUS),
                        lawFirmBuilder.eqKeywordMy(LawFLayer.getSubject(), "%" + LawFLayer.getKeyword() + "%")
                );
    }

    public JPAQuery<LawFirmQ> findUserListMyLawFirm(LawFLayer LawFLayer, Pageable pageable) {
        return jpaQueryFactory
                .select(
                        new QLawFirmQ(
                                userTb.userId,
                                userTb.lawFirmEnrollDt,
                                userTb.nickName,
                                userTb.profile,
                                userTb.status,
                                new CaseBuilder().when(userTb.userId.eq(lawFirmTb.userTb.userId)).then(1).otherwise(0).as("level") // level 1: 마스터, 0 : 일반
                        )
                ).from(userTb)
                .join(lawFirmTb).on(userTb.lawFirmId.lawFirmId.eq(lawFirmTb.lawFirmId))
                .where(
                        userTb.lawFirmId.lawFirmId.eq(LawFLayer.getId()),
                        lawFirmBuilder.eqKeywordUser(LawFLayer.getSubject(), "%" + LawFLayer.getKeyword() + "%")
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }


    public JPAQuery<Long> findUserListMyLawFirmCount(LawFLayer LawFLayer) {
        return jpaQueryFactory
                .select(userTb.count())
                .from(userTb)
                .where(
                        userTb.lawFirmId.lawFirmId.eq(LawFLayer.getId()),
                        lawFirmBuilder.eqKeywordUser(LawFLayer.getSubject(), "%" + LawFLayer.getKeyword() + "%")
                );
    }
}
