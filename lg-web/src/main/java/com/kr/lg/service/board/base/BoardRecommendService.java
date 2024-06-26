package com.kr.lg.service.board.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardRecommendService {

    @Transactional
    void recommendBoard(BoardLayer boardLayer) throws LgException;

    @Transactional
    int deleteRecommendBoard(BoardLayer boardLayer) throws LgException;
}
