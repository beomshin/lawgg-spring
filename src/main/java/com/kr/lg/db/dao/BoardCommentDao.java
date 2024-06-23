package com.kr.lg.db.dao;

import com.kr.lg.web.querydsl.BoardQ;
import com.kr.lg.web.common.layer.BoardLayer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardCommentDao {

    Long findRootComment(Long id);
    Page<BoardQ> findAnonymousParentCommentBoard(BoardLayer requestDto, Pageable pageable);
    Page<BoardQ> findUserParentCommentBoard(BoardLayer requestDto, Pageable pageable);
    Page<BoardQ> findAnonymousChildrenCommentBoard(BoardLayer requestDto, Pageable pageable);
    Page<BoardQ> findUserChildrenCommentBoard(BoardLayer requestDto, Pageable pageable);
}
