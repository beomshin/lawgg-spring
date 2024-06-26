package com.kr.lg.mapper;

import com.kr.lg.model.web.querydsl.BoardQ;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCommentTbMapper {
    List<BoardQ> findAnonymousAllCommentBoard(Long rootId);
    List<BoardQ> findUserAllCommentBoard(Long rootId, Long userId);
}
