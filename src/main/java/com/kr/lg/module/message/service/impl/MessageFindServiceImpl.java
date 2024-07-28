
package com.kr.lg.module.message.service.impl;

import com.kr.lg.module.message.exception.MessageException;
import com.kr.lg.module.message.exception.MessageResultCode;
import com.kr.lg.module.message.mapper.MessageMapper;
import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.web.dto.mapper.MessageParam;
import com.kr.lg.module.message.service.MessageFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageFindServiceImpl implements MessageFindService {

    private final MessageMapper messageMapper;

    @Override
    public Page<MessageEntry> findMessage(MessageParam<?> param) throws MessageException {
        try {
            log.info("▶ [메세지] 메세지 리스트 조회");
            List<MessageEntry> content = messageMapper.findMessages(param); // board 조회
            long count = messageMapper.findMessagesCnt(param.getData()); // board 개수 조회
            return new PageImpl<>(content, param.getPageable(), count); // pageable 생성
        } catch (RuntimeException e) {
            log.error("", e);
            throw new MessageException(MessageResultCode.FAIL_FIND_MESSAGES);
        }
    }


}
