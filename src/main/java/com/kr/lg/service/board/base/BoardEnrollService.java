package com.kr.lg.service.board.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardEnrollService {

    @Transactional
    boolean enrollUserBoard(BoardLayer boardLayer) throws LgException;
    @Transactional
    boolean enrollAnonymousBoard(BoardLayer boardLayer) throws LgException;
    @Transactional
    boolean enrollLawFirmBoard(BoardLayer boardLayer) throws LgException;

}
