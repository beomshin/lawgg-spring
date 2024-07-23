package com.kr.lg.module.board.service;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardLoginService {

    void loginAnonymousBoard(BoardLayer boardLayer) throws LgException;
    void loginUserBoard(BoardLayer boardLayer) throws LgException;

}
