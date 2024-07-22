package com.kr.lg.module.board.service;

import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.dto.BoardEnrollDto;

import java.util.List;

public interface BoardEnrollService {

    BoardTb enrollBoard(BoardEnrollDto enrollDto) throws BoardException;

    <T> void enrollBoardFiles(BoardTb boardTb, List<T> files) throws BoardException;
}
