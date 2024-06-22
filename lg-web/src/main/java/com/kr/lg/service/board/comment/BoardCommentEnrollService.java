package com.kr.lg.service.board.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardCommentEnrollService {

    @Transactional
    void enrollUserCommentBoard(BoardLayer boardLayer) throws LgException;

    @Transactional
    void enrollAnonymousCommentBoard(BoardLayer boardLayer) throws LgException;

}
