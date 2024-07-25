package com.kr.lg.module.board.mapper;

import com.kr.lg.module.board.model.entry.BoardCommentEntry;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardCommentMapper {

    List<BoardCommentEntry> findBoardCommentsWithNotLogin(long rootId);

    List<BoardCommentEntry> findBoardCommentsWithLogin(long rootId, long userId);
}
