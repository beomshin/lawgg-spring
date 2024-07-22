package com.kr.lg.module.board.service;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.module.board.exception.BoardException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardDeleteService {

    @Transactional
    int  deleteAnonymousBoard(BoardLayer boardLayer) throws LgException;
    @Transactional
    int deleteUserBoard(BoardLayer boardLayer) throws LgException;
    void deleteBoard(long boardId) throws BoardException;

}
