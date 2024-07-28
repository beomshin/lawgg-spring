package com.kr.lg.module.message.service;

import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.web.dto.mapper.MessageParam;
import org.springframework.data.domain.Page;

public interface MessageFindService {

    Page<MessageEntry> findMessage(MessageParam<?> param) throws MessageException;
}
