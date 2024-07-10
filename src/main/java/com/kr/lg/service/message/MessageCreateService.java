package com.kr.lg.service.message;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.common.layer.MainLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MessageCreateService {

    @Transactional
    void sendMessage(MainLayer messageDto) throws LgException;
    @Transactional
    void replyMessage(MainLayer messageDto) throws LgException;

}