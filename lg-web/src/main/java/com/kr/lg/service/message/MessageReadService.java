package com.kr.lg.service.message;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.layer.MainLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MessageReadService {

    DefaultResponse findReceiveListMessage(MainLayer messageDto) throws LgException;
    DefaultResponse findSendListMessage(MainLayer messageDto) throws LgException;
    @Transactional
    DefaultResponse findReceiveMessage(MainLayer messageDto) throws LgException;
    DefaultResponse findSendMessage(MainLayer messageDto) throws LgException;
}
