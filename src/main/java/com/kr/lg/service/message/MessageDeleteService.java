package com.kr.lg.service.message;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.common.layer.MainLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MessageDeleteService {

    @Transactional
    void deleteSendMessage(MainLayer messageDto) throws LgException;

    @Transactional
    void deleteReceiveMessage(MainLayer messageDto) throws LgException;
}
