package com.kr.lg.module.board.service;

import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.board.exception.BoardException;

public interface BoardRecommendService {
    void recommendBoard(BoardTb boardTb, UserTb userTb) throws BoardException;
}
