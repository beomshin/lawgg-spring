package com.kr.lg.module.board.mapper;

import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.web.dto.mapper.board.BoardParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFindMapper {

    List<BoardEntry> findBoards(BoardParam<?> entry);
}
