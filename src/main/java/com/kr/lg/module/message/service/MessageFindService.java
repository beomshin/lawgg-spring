package com.kr.lg.module.message.service;

import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.model.entry.MessageEntry;
import org.springframework.data.domain.Page;
import com.kr.lg.model.mapper.MessageParam;

public interface MessageFindService {

    Page<MessageEntry> findMessage(MessageParam<?> param) throws MessageException;
}
