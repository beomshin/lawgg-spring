package com.kr.lg.service.message.impl;

import com.kr.lg.db.entities.MessageTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.common.enums.entity.element.ReplyEnum;
import com.kr.lg.db.repositories.MessageRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.model.common.global.GlobalCode;
import com.kr.lg.common.utils.MessageUtils;
import com.kr.lg.model.common.layer.MainLayer;
import com.kr.lg.service.message.MessageCreateService;
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

    @Override
    public void replyMessage(MainLayer messageDto) throws LgException {
        MessageTb messageTb = messageRepository.findById(messageDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_MESSAGE)); // 수신 메세지
        messageUtils.checkReceiver(messageTb.getReceiver(), messageDto.getUserTb()); // 발송자가 수신한 메세지 맞는지 확인
        messageRepository.save(MessageTb.builder()
                .receiver(messageDto.getSender())
                .sender(messageDto.getUserTb())
                .title(messageDto.getTitle())
                .content(messageDto.getContent())
                .build()); // 회신하기
        messageRepository.reply(messageTb.getMessageId(), ReplyEnum.REPLY_FLAG);
    }
}
