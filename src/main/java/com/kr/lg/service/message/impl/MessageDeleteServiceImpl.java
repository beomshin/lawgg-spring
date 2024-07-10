package com.kr.lg.service.message.impl;

import com.kr.lg.db.entities.MessageTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.enums.DelEnum;
import com.kr.lg.db.repositories.MessageRepository;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.common.utils.MessageUtils;
import com.kr.lg.model.common.layer.MainLayer;
import com.kr.lg.service.message.MessageDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageDeleteServiceImpl implements MessageDeleteService {

    private final MessageRepository messageRepository;
    private final MessageUtils messageUtils;

    @Override
    public void deleteSendMessage(MainLayer messageDto) throws LgException {
        MessageTb messageTb = messageRepository.findById(messageDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_MESSAGE)); // 발신 메세지
        messageUtils.neSelf(messageTb.getSender(), messageDto.getUserTb()); // 발신자가 아닌경우
        if (messageTb.getSenderDelFlag().equals(DelEnum.DELETE_STATUS)) throw new LgException(GlobalCode.DELETE_MESSAGE);
        messageRepository.deleteSender(messageTb.getMessageId(), DelEnum.DELETE_STATUS);
    }

    @Override
    public void deleteReceiveMessage(MainLayer messageDto) throws LgException {
        MessageTb messageTb = messageRepository.findById(messageDto.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_MESSAGE)); // 발신 메세지
        messageUtils.neSelf(messageTb.getReceiver(), messageDto.getUserTb()); // 수신자가 아닌경우
        if (messageTb.getSenderDelFlag().equals(DelEnum.DELETE_STATUS)) throw new LgException(GlobalCode.DELETE_MESSAGE);
        messageRepository.deleteReceiver(messageTb.getMessageId(), DelEnum.DELETE_STATUS);
    }

}

