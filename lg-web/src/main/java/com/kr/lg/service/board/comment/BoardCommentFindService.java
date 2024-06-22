package com.kr.lg.service.board.comment;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardCommentFindService {

    DefaultResponse findAnonymousAllCommentBoard(BoardLayer boardLayer) throws LgException;
    DefaultResponse findUserAllCommentBoard(BoardLayer boardLayer) throws LgException;
    DefaultResponse findAnonymousParentCommentBoard(BoardLayer boardLayer) throws LgException;
    DefaultResponse findUserParentCommentBoard(BoardLayer boardLayer) throws LgException;
    DefaultResponse findAnonymousChildrenCommentBoard(BoardLayer boardLayer) throws LgException;
    DefaultResponse findUserChildrenCommentBoard(BoardLayer boardLayer) throws LgException;

}
