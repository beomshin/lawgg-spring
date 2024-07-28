package com.kr.lg.module.message.service.impl;

import com.kr.lg.db.entities.MessageTb;
import com.kr.lg.db.repositories.MessageRepository;
import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.exception.MessageResultCode;
import com.kr.lg.module.message.model.dto.MessageEnrollDto;
import com.kr.lg.module.message.service.MessageEnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageEnrollServiceImpl implements MessageEnrollService {

    private final MessageRepository messageRepository;

    @Override
    @Transactional
    public void sendMessage(MessageEnrollDto enrollDto) throws MessageException {
        try {
            log.info("▶ [메세지] 메세지 전송");
            MessageTb messageTb = MessageTb.builder()
                    .receiver(enrollDto.getReceiver())
                    .sender(enrollDto.getSender())
                    .title(enrollDto.getTitle())
                    .content(enrollDto.getContent())
                    .build();
            messageRepository.save(messageTb);
        } catch (Exception e) {
            log.error("", e);
            throw new MessageException(MessageResultCode.FAIL_SEND_MESSAGES);
        }
    }

}
