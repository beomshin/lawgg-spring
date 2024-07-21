package com.kr.lg.module.board.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
import com.kr.lg.module.board.model.req.FindLawFirmBoardRequest;
import com.kr.lg.module.board.model.req.FindMyBoardRequest;
import org.springframework.data.domain.Page;

public interface BoardService {

    Page<BoardEntry> findBoards(FindBoardRequest request) throws BoardException;
    Page<BoardEntry> findUserBoards(FindMyBoardRequest request, UserTb userTb) throws BoardException;
    Page<BoardEntry> findLawFirmBoards(FindLawFirmBoardRequest request) throws BoardException;
}
