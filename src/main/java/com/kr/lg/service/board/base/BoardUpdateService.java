package com.kr.lg.service.board.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.web.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardUpdateService {

    @Transactional
    int updateUserBoard(BoardLayer boardLayer) throws LgException;
    @Transactional
    int updateAnonymousBoard(BoardLayer boardLayer) throws LgException;

}
