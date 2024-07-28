package com.kr.lg.module.message.service.impl;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.exception.MessageResultCode;
import com.kr.lg.module.message.model.dto.MessageEnrollDto;
import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.module.message.model.mapper.FindMessageParamData;
import com.kr.lg.module.message.model.req.FindReceiveMessagesRequest;
import com.kr.lg.module.message.model.req.SendMessageRequest;
import com.kr.lg.module.message.service.MessageEnrollService;
import com.kr.lg.module.message.service.MessageFindService;
import com.kr.lg.module.message.service.MessageService;
import com.kr.lg.module.message.sort.MessageSort;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.MessageParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageEnrollService messageEnrollService;
    private final MessageFindService messageFindService;
    private final UserRepository userRepository;

    @Override
    public Page<MessageEntry> findReceiveMessages(FindReceiveMessagesRequest request, UserTb userTb) throws MessageException {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), MessageSort.idDesc()); // pageable 생성
        MapperParam param = FindMessageParamData.builder()
                .receiverId(userTb.getUserId())
                .subject(request.getSubject())
                .keyword(request.getKeyword())
                .build();
        return messageFindService.findMessage(new MessageParam<>(param, pageable));
    }

    @Override
    public void sendMessage(SendMessageRequest request, UserTb userTb) throws MessageException {
        Optional<UserTb> receiver = userRepository.findByNickName(request.getReceiver());
        if (receiver.isPresent()) {
            if (Objects.equals(receiver.get().getUserId(), userTb.getUserId())) throw new MessageException(MessageResultCode.NOT_SEND_SELF); // 자신에게 발송 거절
            MessageEnrollDto enrollDto  = MessageEnrollDto.builder()
                    .receiver(receiver.get())
                    .title(request.getTitle())
                    .content(request.getContent())
                    .sender(userTb)
                    .build();
            messageEnrollService.sendMessage(enrollDto);
        } else  {
            throw new MessageException(MessageResultCode.NOT_EXIST_MEMBER);
        }
    }
}
