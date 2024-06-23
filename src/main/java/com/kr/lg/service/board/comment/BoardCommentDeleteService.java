package com.kr.lg.service.board.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.web.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardCommentDeleteService {

    @Transactional
    int deleteAnonymousCommentBoard(BoardLayer boardLayer) throws LgException;

    @Transactional
    int deleteUserCommentBoard(BoardLayer boardLayer) throws LgException;
}
