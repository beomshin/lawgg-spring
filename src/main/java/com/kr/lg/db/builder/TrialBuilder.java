package com.kr.lg.db.builder;

import com.kr.lg.common.enums.TrialSubjectEnum;
import com.kr.lg.common.enums.TrialTopicEnum;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.kr.lg.db.entities.QTrialTb.trialTb;

@Component
public class TrialBuilder {

    private final String POST_TYPE_ORDER_BY = "CASE WHEN postType = 99 THEN 2 WHEN postType = 98 THEN 1 ELSE 0 END";
    private final String DATE_FORMAT_ORDER_BY = "DATE_FORMAT({0}, {1})";

    public BooleanExpression eqSubject(TrialSubjectEnum subject, String keyword) {
        switch (subject) {
            case ALL_TYPE: return trialTb.title.contains(keyword).or(trialTb.content.contains(keyword)).or(trialTb.writer.contains(keyword));
            case TITLE_TYPE: return trialTb.title.contains(keyword);
            case CONTENT_TYPE: return trialTb.content.contains(keyword);
            case WRITER_TYPE: return trialTb.writer.contains(keyword);
            default: return null;
        }
    } // 검색조건

    public List<OrderSpecifier> orderTopic(TrialTopicEnum topic) {
        List<OrderSpecifier> orders = new ArrayList<>();
        if (TrialTopicEnum.ALL_TOPIC.equals(topic)) {
            StringTemplate postType = Expressions.stringTemplate(POST_TYPE_ORDER_BY);
            orders.add(postType.desc());
            StringTemplate writeDt = Expressions.stringTemplate(DATE_FORMAT_ORDER_BY, trialTb.writeDt, ConstantImpl.create("%Y-%m-%d %H:%i:%s"));
            orders.add(writeDt.desc());
        } else if (TrialTopicEnum.LIVE_TOPIC.equals(topic)) {
            StringTemplate postType = Expressions.stringTemplate(POST_TYPE_ORDER_BY);
            orders.add(postType.desc());
            StringTemplate writeDt = Expressions.stringTemplate(DATE_FORMAT_ORDER_BY, trialTb.writeDt, ConstantImpl.create("%Y-%m-%d"));
            orders.add(writeDt.desc());
            orders.add(trialTb.liveType.desc());
            orders.add(trialTb.endingType.desc());
        }
        return orders;
    }
}
