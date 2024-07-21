package com.kr.lg.module.board.service;

import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.mapper.board.BoardParam;
import org.springframework.data.domain.Page;

public interface BoardFindService {

    Page<BoardEntry> findBoards(BoardParam<?> param) throws BoardException;
}
