package com.kr.lg.db.dao;

import com.kr.lg.web.common.layer.MainLayer;
import com.kr.lg.web.querydsl.MessageQ;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MessageDao {

    Page<MessageQ> findReceiveListMessage(MainLayer requestDto, Pageable pageable);
    Page<MessageQ> findSendListMessage(MainLayer requestDto, Pageable pageable);
    Optional<MessageQ> readMessage(MainLayer requestDto);
    
}
