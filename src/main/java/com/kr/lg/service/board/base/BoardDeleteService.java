package com.kr.lg.service.board.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardDeleteService {

    @Transactional
    int  deleteAnonymousBoard(BoardLayer boardLayer) throws LgException;
    @Transactional
    int deleteUserBoard(BoardLayer boardLayer) throws LgException;

}
