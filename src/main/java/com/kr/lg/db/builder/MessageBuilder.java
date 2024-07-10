package com.kr.lg.db.builder;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Component;

import static com.kr.lg.db.entities.QMessageTb.messageTb;
@Component
public class MessageBuilder {

    public BooleanExpression eqSubject(Integer subject, String  keyword) {
        switch (subject) {
            case 0: return messageTb.title.like(keyword)
                    .or(messageTb.content.like(keyword))
                    .or(messageTb.receiver.nickName.like(keyword))
                    .or(messageTb.sender.nickName.like(keyword));
            case 1: return messageTb.title.like(keyword);
            case 2: return messageTb.content.like(keyword);
            case 3: return messageTb.receiver.nickName.like(keyword);
            case 4: return messageTb.sender.nickName.like(keyword);
            default: return null;
        }
    }
}
