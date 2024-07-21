package com.kr.lg.module.board.mapper;

import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.BoardParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFindMapper {

    List<BoardEntry> findBoards(BoardParam<?> param);

    long findBoardsCnt(MapperParam param);

    BoardEntry findBoard(MapperParam param);
}
