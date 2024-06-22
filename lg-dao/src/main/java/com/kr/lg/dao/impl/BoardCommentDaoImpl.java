package com.kr.lg.dao.impl;

import com.kr.lg.model.web.querydsl.BoardQ;
import com.kr.lg.model.web.common.layer.BoardLayer;
import com.kr.lg.dao.BoardCommentDao;
import com.kr.lg.query.BoardCommentQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardCommentDaoImpl implements BoardCommentDao {

    private final BoardCommentQuery boardCommentQuery;

    @Override
    public Long findRootComment(Long id) {
        return boardCommentQuery.findRootComment(id).fetchOne(); // 루트 댓글 식별자 조회
    }

    @Override
    public Page<BoardQ> findAnonymousParentCommentBoard(BoardLayer requestDto, Pageable pageable) {
        JPAQuery<BoardQ> content = boardCommentQuery.findAnonymousParentCommentBoard(requestDto, pageable);
        JPAQuery<Long> count = boardCommentQuery.findAnonymousParentCommentBoardCount(requestDto);
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Page<BoardQ> findUserParentCommentBoard(BoardLayer requestDto, Pageable pageable) {
        JPAQuery<BoardQ> content = boardCommentQuery.findUserParentCommentBoard(requestDto, pageable);
        JPAQuery<Long> count = boardCommentQuery.findUserParentCommentBoardCount(requestDto);
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Page<BoardQ> findAnonymousChildrenCommentBoard(BoardLayer requestDto, Pageable pageable) {
        JPAQuery<BoardQ> content = boardCommentQuery.findAnonymousChildrenCommentBoard(requestDto, pageable);
        JPAQuery<Long> count = boardCommentQuery.findAnonymousChildrenCommentBoardCount(requestDto);
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }

    @Override
    public Page<BoardQ> findUserChildrenCommentBoard(BoardLayer requestDto, Pageable pageable) {
        JPAQuery<BoardQ> content = boardCommentQuery.findUserChildrenCommentBoard(requestDto, pageable);
        JPAQuery<Long> count = boardCommentQuery.findUserChildrenCommentBoardCount(requestDto);
        return PageableExecutionUtils.getPage(content.fetch(), pageable, count::fetchOne);
    }
}
