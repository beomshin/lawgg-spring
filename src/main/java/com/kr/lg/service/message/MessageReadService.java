package com.kr.lg.service.message;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.common.layer.MainLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MessageReadService {

    DefaultResponse findReceiveListMessage(MainLayer messageDto) throws LgException;
    DefaultResponse findSendListMessage(MainLayer messageDto) throws LgException;
    @Transactional
    DefaultResponse findReceiveMessage(MainLayer messageDto) throws LgException;
    DefaultResponse findSendMessage(MainLayer messageDto) throws LgException;
}
