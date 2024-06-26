package com.kr.lg.service.board.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardCommentReportService {

    @Transactional
    int reportCommentBoard(BoardLayer boardLayer) throws LgException;
}
