package com.kr.lg.db.query;

import com.kr.lg.enums.DelEnum;
import com.kr.lg.db.builder.MessageBuilder;
import com.kr.lg.model.querydsl.MessageQ;
import com.kr.lg.model.querydsl.QMessageQ;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.kr.lg.model.common.layer.MainLayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.kr.lg.db.entities.QMessageTb.messageTb;
import static com.kr.lg.db.entities.QUserTb.userTb;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageQuery {

    private final JPAQueryFactory jpaQueryFactory;

    private final MessageBuilder messageBuilder;


    public JPAQuery<MessageQ> findReceiveListMessage(MainLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QMessageQ(
                        messageTb.messageId,
                        messageTb.sender.loginId,
                        messageTb.sender.nickName,
                        messageTb.title,
                        messageTb.content,
                        messageTb.readFlag,
                        messageTb.regDt
                ))
                .from(messageTb)
                .join(userTb).on(messageTb.sender.userId.eq(userTb.userId))
                .where(
                        messageTb.receiver.userId.eq(requestDto.getUserTb().getUserId()),
                        messageBuilder.eqSubject(requestDto.getSubject(), "%" + requestDto.getKeyword() + "%"),
                        messageTb.receiverDelFlag.eq(DelEnum.NON_DELETE_STATUS)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }


    public JPAQuery<Long> findReceiveListMessageCount(MainLayer requestDto) {
        return jpaQueryFactory
                .select(messageTb.count())
                .from(messageTb)
                .where(
                        messageTb.receiver.userId.eq(requestDto.getUserTb().getUserId()),
                        messageBuilder.eqSubject(requestDto.getSubject(), "%" + requestDto.getKeyword() + "%"),
                        messageTb.receiverDelFlag.eq(DelEnum.NON_DELETE_STATUS)
                );
    }
    public JPAQuery<MessageQ> findSendListMessage(MainLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QMessageQ(
                        messageTb.messageId,
                        messageTb.receiver,
                        messageTb.sender,
                        messageTb.title,
                        messageTb.content,
                        messageTb.readFlag,
                        messageTb.regDt
                ))
                .from(messageTb)
                .join(userTb).on(messageTb.receiver.userId.eq(userTb.userId))
                .join(userTb).on(messageTb.sender.userId.eq(userTb.userId))
                .where(
                        messageTb.sender.userId.eq(requestDto.getUserTb().getUserId()),
                        messageBuilder.eqSubject(requestDto.getSubject(), "%" + requestDto.getKeyword() + "%"),
                        messageTb.senderDelFlag.eq(DelEnum.NON_DELETE_STATUS)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    public JPAQuery<Long> findSendListMessageCount(MainLayer requestDto) {
        return jpaQueryFactory
                .select(messageTb.count())
                .from(messageTb)
                .where(
                        messageTb.sender.userId.eq(requestDto.getUserTb().getUserId()),
                        messageBuilder.eqSubject(requestDto.getSubject(), "%" + requestDto.getKeyword() + "%"),
                        messageTb.senderDelFlag.eq(DelEnum.NON_DELETE_STATUS)
                );
    }


    public JPAQuery<MessageQ> readMessage(MainLayer requestDto) {
        return jpaQueryFactory
                .select(new QMessageQ(
                        messageTb.messageId,
                        messageTb.receiver,
                        messageTb.sender,
                        messageTb.title,
                        messageTb.content,
                        messageTb.readFlag,
                        messageTb.regDt,
                        messageTb.receiverDelFlag,
                        messageTb.senderDelFlag
                ))
                .from(messageTb)
                .join(userTb).on(messageTb.receiver.userId.eq(userTb.userId))
                .join(userTb).on(messageTb.sender.userId.eq(userTb.userId))
                .where(
                        messageTb.messageId.eq(requestDto.getId())
                );
    }
}
