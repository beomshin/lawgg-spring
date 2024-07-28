package com.kr.lg.module.message.service.impl;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.module.message.model.mapper.FindMessageParamData;
import com.kr.lg.module.message.model.req.FindRMLRequest;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageFindService messageFindService;

    @Override
    public Page<MessageEntry> findReceiveMessages(FindRMLRequest request, UserTb userTb) throws MessageException {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), MessageSort.idDesc()); // pageable 생성
        MapperParam param = FindMessageParamData.builder()
                .receiverId(userTb.getUserId())
                .subject(request.getSubject())
                .keyword(request.getKeyword())
                .build();
        return messageFindService.findMessage(new MessageParam<>(param, pageable));
    }
}
