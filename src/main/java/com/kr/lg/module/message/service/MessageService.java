package com.kr.lg.module.message.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.module.message.model.req.FindMessagesRequest;
import com.kr.lg.module.message.model.req.SendMessageRequest;
import org.springframework.data.domain.Page;

public interface MessageService {

    Page<MessageEntry> findReceiveMessages(FindMessagesRequest request, UserTb userTb) throws MessageException;
    void sendMessage(SendMessageRequest request, UserTb userTb) throws MessageException;
}
