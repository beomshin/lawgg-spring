package com.kr.lg.service.board.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardCommentUpdateService {

    @Transactional
    int updateAnonymousCommentBoard(BoardLayer boardLayer) throws LgException;

    @Transactional
    int updateUserCommentBoard(BoardLayer boardLayer) throws LgException;

}
