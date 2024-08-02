package com.kr.lg.module.message.mapper;

import com.kr.lg.module.message.model.entry.MessageEntry;
import com.kr.lg.model.mapper.MapperParam;
import com.kr.lg.model.mapper.MessageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    List<MessageEntry> findMessages(MessageParam<?> param);
    long findMessagesCnt(MapperParam param);
}
