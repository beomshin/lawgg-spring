package com.kr.lg.module.message.service;

import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.model.dto.MessageEnrollDto;

public interface MessageEnrollService {
    void sendMessage(MessageEnrollDto enrollDto) throws MessageException;
}
