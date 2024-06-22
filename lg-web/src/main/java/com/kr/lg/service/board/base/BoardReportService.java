package com.kr.lg.service.board.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardReportService {

    @Transactional
    int reportBoard(BoardLayer boardLayer) throws LgException;
}
