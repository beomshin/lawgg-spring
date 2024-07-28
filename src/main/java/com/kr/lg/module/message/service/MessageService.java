package com.kr.lg.module.message.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.module.message.model.req.FindRMLRequest;
import org.springframework.data.domain.Page;

public interface MessageService {

    Page<MessageEntry> findReceiveMessages(FindRMLRequest request, UserTb userTb) throws MessageException;
}
