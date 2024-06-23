package com.kr.lg.db.builder;

import com.kr.lg.common.enums.ApplyTypeEnum;
import com.kr.lg.common.enums.LawFirmTypeEnum;
import com.kr.lg.common.enums.LawFirmUserTypeEnum;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Component;

import static com.kr.lg.db.entities.QLawFirmTb.lawFirmTb;
import static com.kr.lg.db.entities.QLawFirmApplyTb.lawFirmApplyTb;
import static com.kr.lg.db.entities.QUserTb.userTb;
@Component
public class LawFirmBuilder {

    public BooleanExpression eqKeyword(Integer subject, String keyword) {
        if (subject == null) return null;
        switch (LawFirmTypeEnum.of(subject)) {
            case ALL_TYPE: return lawFirmTb.name.like(keyword).or(userTb.nickName.like(keyword));
            case LAW_FIRM_NAME_TYPE: return lawFirmTb.name.like(keyword);
            case REP_NAME_TYPE: return userTb.nickName.like(keyword);
            default: return null;
        }
    }


    public BooleanExpression eqKeywordMy(Integer subject, String keyword) {
        if (subject == null) return null;

        switch (ApplyTypeEnum.of(subject)) {
            case ALL_TYPE: return null;
            case NAME_TYPE: return lawFirmApplyTb.userTb.nickName.like(keyword);
            default:return null;
        }
    }

    public BooleanExpression eqKeywordUser(Integer subject, String keyword) {
        if (subject == null) return null;

        switch (LawFirmUserTypeEnum.of(subject)) {
            case ALL_TYPE: return null;
            case NAME_TYPE: return userTb.nickName.like(keyword);
            default:return null;
        }
    }

}
