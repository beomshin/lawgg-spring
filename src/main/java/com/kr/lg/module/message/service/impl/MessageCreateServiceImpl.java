package com.kr.lg.module.message.service.impl;

import com.kr.lg.db.entities.MessageTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.db.repositories.MessageRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.common.utils.MessageUtils;
import com.kr.lg.model.common.layer.MainLayer;
import com.kr.lg.module.message.service.MessageCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageCreateServiceImpl implements MessageCreateService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageUtils messageUtils;

    @Override
    public void sendMessage(MainLayer messageDto) throws LgException {
        UserTb userTb = userRepository.findByNickName(messageDto.getReceiver()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_MEMBER));
        messageUtils.checkSelfSend(userTb, messageDto.getSender()); // 자신에게 발송 거절
        messageRepository.save(MessageTb.builder()
                .receiver(userTb)
                .sender(messageDto.getSender())
                .title(messageDto.getTitle())
                .content(messageDto.getContent())
                .build());
    }

}
