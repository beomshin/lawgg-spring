package com.kr.lg.module.message.mapper;

import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.MessageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    List<MessageEntry> findMessages(MessageParam<?> param);
    long findMessagesCnt(MapperParam param);
}
