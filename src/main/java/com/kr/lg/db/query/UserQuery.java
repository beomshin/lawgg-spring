package com.kr.lg.db.query;

import com.kr.lg.common.enums.entity.element.SnsEnum;
import com.kr.lg.db.entities.AlertTb;
import com.kr.lg.model.common.layer.UserLayer;
import com.kr.lg.model.querydsl.AlertQ;
import com.kr.lg.model.querydsl.QAlertQ;
import com.kr.lg.model.querydsl.QUserQ;
import com.kr.lg.model.querydsl.UserQ;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.kr.lg.db.entities.QUserTb.userTb;
import static com.kr.lg.db.entities.QLawFirmTb.lawFirmTb;
import static com.kr.lg.db.entities.QTierTb.tierTb;
import static com.kr.lg.db.entities.QAlertTb.alertTb;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserQuery {

    private final JPAQueryFactory jpaQueryFactory;

    public JPAQuery<UserQ> findIdUser(UserLayer requestDto) {
        log.debug("[findIdUser] 아이디 찾기 ci 조회");
        return jpaQueryFactory
                .select(new QUserQ(
                        userTb.loginId,
                        userTb.profile,
                        userTb.nickName,
                        userTb.delFlag,
                        userTb.status,
                        userTb.regDt,
                        userTb.snsType
                ))
                .from(userTb)
                .where(
                        userTb.ci.eq(requestDto.getCi()),
                        userTb.snsType.eq(SnsEnum.LG_SNS_TYPE)
                );
    }

    public JPAQuery<UserQ> findInfoUser(UserLayer requestDto) {
        return jpaQueryFactory
                .select(new QUserQ(
                        userTb.userId,
                        userTb.loginId,
                        userTb.nickName,
                        userTb.name,
                        lawFirmTb.name,
                        tierTb.name,
                        tierTb.level,
                        userTb.profile,
                        userTb.email,
                        userTb.boardCount,
                        userTb.commentCount,
                        userTb.trialCount,
                        userTb.personalPeriod,
                        userTb.regDt,
                        userTb.phone,
                        userTb.authFlag,
                        userTb.snsType,
                        userTb.judgeFlag,
                        userTb.gender,
                        userTb.birth,
                        userTb.point
                ))
                .from(userTb)
                .leftJoin(lawFirmTb).on(lawFirmTb.lawFirmId.eq(userTb.lawFirmId.lawFirmId))
                .leftJoin(tierTb).on(tierTb.tierId.eq(userTb.tierId.tierId))
                .where(
                        userTb.userId.eq(requestDto.getUserTb().getUserId())
                );
    }

    public JPAQuery<AlertQ> findUserAlert(UserLayer requestDto, Pageable pageable) {
        return jpaQueryFactory.select(
                new QAlertQ(
                        alertTb.alertId,
                        alertTb.trialTb.trialId,
                        alertTb.boardTb.boardId,
                        alertTb.title,
                        alertTb.content,
                        alertTb.type,
                        alertTb.readFlag,
                        alertTb.regDt
                ))
                .from(alertTb)
                .where(
                        alertTb.userTb.userId.eq(requestDto.getUserTb().getUserId()),
                        this.containKeyword(requestDto.getKeyword())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(alertTb.regDt.desc());
    }

    public JPAQuery<Long> findUserAlertCount(UserLayer requestDto) {
        return jpaQueryFactory
                .select(alertTb.count())
                .from(alertTb)
                .leftJoin(userTb).on(userTb.userId.eq(alertTb.userTb.userId))
                .where(
                        userTb.userId.eq(requestDto.getUserTb().getUserId())
                );
    }

    public JPAQuery<AlertTb> findTop5Alert(Long userId, Pageable pageable) {
        return jpaQueryFactory.select(alertTb)
                .from(alertTb)
                .where(
                        alertTb.userTb.userId.eq(userId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(alertTb.regDt.desc());
    }

    public BooleanExpression containKeyword(String keyword) {
        if (StringUtils.isBlank(keyword)) return null;
        return alertTb.title.contains(keyword);
    } // 검색조건

}
