package com.kr.lg.dao.impl;

import com.kr.lg.model.web.common.layer.MainLayer;
import com.kr.lg.model.web.querydsl.MessageQ;
import com.kr.lg.dao.MessageDao;
import com.kr.lg.query.MessageQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MessageDaoImpl implements MessageDao {

    private final MessageQuery messageQuery;

    @Override
    public Page<MessageQ> findReceiveListMessage(MainLayer requestDto, Pageable pageable) {
        JPAQuery<MessageQ> content = messageQuery.findReceiveListMessage(requestDto, pageable);
        JPAQuery<Long> count = messageQuery.findReceiveListMessageCount(requestDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Page<MessageQ> findSendListMessage(MainLayer requestDto, Pageable pageable) {
        JPAQuery<MessageQ> content = messageQuery.findSendListMessage(requestDto, pageable);
        JPAQuery<Long> count = messageQuery.findSendListMessageCount(requestDto); // 게시판 카운터
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Optional<MessageQ> readMessage(MainLayer messageDto) {
        return Optional.ofNullable(messageQuery.readMessage(messageDto).fetchOne());
    }

}
