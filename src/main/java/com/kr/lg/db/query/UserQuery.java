package com.kr.lg.db.query;

import com.kr.lg.common.enums.entity.type.SnsType;
import com.kr.lg.db.entities.AlertTb;
import com.kr.lg.model.common.layer.UserLayer;
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

}
