package com.kr.lg.module.board.service;

import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
import org.springframework.data.domain.Page;

public interface BoardService {

    Page<BoardEntry> findBoards(FindBoardRequest request);
}
