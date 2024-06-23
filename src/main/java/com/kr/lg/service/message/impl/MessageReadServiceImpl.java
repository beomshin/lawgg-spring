
package com.kr.lg.service.message.impl;

import com.kr.lg.exception.LgException;
import com.kr.lg.db.dao.MessageDao;
import com.kr.lg.enums.entity.element.ReadEnum;
import com.kr.lg.web.querydsl.MessageQ;
import com.kr.lg.db.repositories.MessageRepository;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.utils.MessageUtils;
import com.kr.lg.web.common.layer.MainLayer;
import com.kr.lg.web.net.response.message.FindRMResponse;
import com.kr.lg.web.net.response.message.FindSMResponse;
import com.kr.lg.web.net.response.message.ReceiveMLResponse;
import com.kr.lg.web.net.response.message.SendMLResponse;
import com.kr.lg.service.message.MessageReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageReadServiceImpl implements MessageReadService {

    private final MessageDao messageDao;
    private final MessageRepository messageRepository;
    private final MessageUtils messageUtils;


    @Override
    public DefaultResponse findReceiveListMessage(MainLayer messageDto) throws LgException {
        Page<MessageQ> messageList = messageDao.findReceiveListMessage(messageDto, PageRequest.of(messageDto.getPage(), messageDto.getPageNum()));
        return new ReceiveMLResponse(messageList);
    }

    @Override
    public DefaultResponse findSendListMessage(MainLayer messageDto) throws LgException {
        Page<MessageQ> messageList = messageDao.findSendListMessage(messageDto, PageRequest.of(messageDto.getPage(), messageDto.getPageNum()));
        return new SendMLResponse(messageList);
    }

    @Override
    public DefaultResponse findReceiveMessage(MainLayer messageDto) throws LgException {
        MessageQ message = messageDao.readMessage(messageDto).orElseThrow(() -> new LgException(GlobalCode.FAIL_ACCESS));
        messageUtils.neSelf(message.getReceiver(), messageDto.getUserTb()); // 수신자 체크
        message.isDelete(); // 삭제 확인
        if (!message.isRead()) messageRepository.readMessage(messageDto.getId(), ReadEnum.READ_FLAG); // 읽음 처리
        return new FindRMResponse(message);
    }

    @Override
    public DefaultResponse findSendMessage(MainLayer messageDto) throws LgException {
        MessageQ message = messageDao.readMessage(messageDto).orElseThrow(() -> new LgException(GlobalCode.FAIL_ACCESS));
        messageUtils.neSelf(message.getSender(), messageDto.getUserTb()); // 발신자 체크
        message.isDelete(); // 삭제 확인
        return new FindSMResponse(message);
    }

}
