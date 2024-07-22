package com.kr.lg.module.board.service;

import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.entry.BoardEntry;
import com.kr.lg.web.dto.mapper.BoardParam;
import com.kr.lg.web.dto.mapper.MapperParam;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BoardFindService {

    Page<BoardEntry> findBoards(BoardParam<?> param) throws BoardException;
    Optional<BoardEntry> findBoard(MapperParam param) throws BoardException;
}
