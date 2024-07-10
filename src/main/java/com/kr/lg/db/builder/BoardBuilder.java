package com.kr.lg.db.builder;

import com.kr.lg.enums.BoardTopicEnum;
import com.kr.lg.enums.BoardTypeEnum;
import com.kr.lg.enums.LineEnum;
import com.kr.lg.enums.PostEnum;
import com.kr.lg.enums.StatusEnum;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.kr.lg.db.entities.QBoardTb.boardTb;
import static com.kr.lg.db.entities.QBoardAttachTb.boardAttachTb;

@Component
public class BoardBuilder {

    private final String POST_TYPE_ORDER_BY = "CASE WHEN postType = 99 THEN 2 WHEN postType = 98 THEN 1 ELSE 0 END";
    private final String POST_TYPE_ORDER_BY_2 = "CASE WHEN postType = 3 THEN 2 WHEN postType = 2 THEN 1 ELSE 0 END";
    private final String DATE_FORMAT_ORDER_BY = "DATE_FORMAT({0}, {1})";

    public BooleanExpression eqLineType(LineEnum lineType) {
        if (LineEnum.ALL.equals(lineType)) return null;
        return boardTb.lineType.eq(lineType).or(boardTb.postType.in(PostEnum.NOTICE_TYPE, PostEnum.EVENT_TYPE));
    } // 라인 타입 조회

    public BooleanExpression eqSubject(BoardTypeEnum subject, String keyword) {
        if (subject == null || keyword == null) return null;
        keyword = "%".concat(keyword).concat("%");
        switch (subject) {
            case ALL_TYPE: return boardTb.title.like(keyword).or(boardTb.content.like(keyword)).or(boardTb.writer.like(keyword));
            case TITLE_TYPE: return boardTb.title.like(keyword);
            case CONTENT_TYPE: return boardTb.content.like(keyword);
            case WRITER_TYPE: return boardTb.writer.like(keyword);
            default: return null;
        }
    } // 검색조건

    public BooleanExpression eqStatus(StatusEnum status) {
        return boardTb.status.eq(status);
    }

    public List<OrderSpecifier> orderTopic(BoardTopicEnum topic) {
        List<OrderSpecifier> orders = new ArrayList<>();
        if (BoardTopicEnum.NEW_TOPIC.equals(topic)) {
            StringTemplate postType = Expressions.stringTemplate(POST_TYPE_ORDER_BY);
            orders.add(postType.desc());
            StringTemplate writeDt = Expressions.stringTemplate(DATE_FORMAT_ORDER_BY, boardTb.writeDt, ConstantImpl.create("%Y-%m-%d %H:%i:%s"));
            orders.add(writeDt.desc());
        } else if (BoardTopicEnum.HOT_TOPIC.equals(topic)) {
            StringTemplate postType = Expressions.stringTemplate(POST_TYPE_ORDER_BY);
            orders.add(postType.desc());
            StringTemplate writeDt = Expressions.stringTemplate(DATE_FORMAT_ORDER_BY, boardTb.writeDt, ConstantImpl.create("%Y-%m-%d"));
            orders.add(writeDt.desc());
            StringTemplate postType2 = Expressions.stringTemplate(POST_TYPE_ORDER_BY_2);
            orders.add(postType2.desc());
        }

        return orders;
    } // 정렬 조건 ( 날짜&시간 순, 추천 순)

    public BooleanExpression eqBoardId(Long boardId) { // eq boardId
        return boardTb.boardId.eq(boardId);
    }

    public BooleanExpression eqBoardAttachNormalStatus() { // eq boardId
        return boardAttachTb.status.eq(StatusEnum.NORMAL_STATUS);
    }

}
