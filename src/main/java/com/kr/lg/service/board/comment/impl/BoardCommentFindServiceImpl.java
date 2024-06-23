package com.kr.lg.service.board.comment.impl;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.dao.BoardCommentDao;
import com.kr.lg.mapper.BoardCommentTbMapper;
import com.kr.lg.web.net.response.board.comment.*;
import com.kr.lg.web.querydsl.BoardQ;
import com.kr.lg.web.common.layer.BoardLayer;
import com.kr.lg.service.board.comment.BoardCommentFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardCommentFindServiceImpl implements BoardCommentFindService {

    private final BoardCommentDao boardCommentDao;
    private final BoardCommentTbMapper boardCommentTbMapper;

    @Override
    public DefaultResponse findAnonymousAllCommentBoard(BoardLayer boardLayer) {
        Long rootId = boardCommentDao.findRootComment(boardLayer.getId()); // 루트 댓글 조회
        List<BoardQ> comments = boardCommentTbMapper.findAnonymousAllCommentBoard(rootId); // 댓글 리스트 조회
        return new FindABCResponse(rootId, comments);
    }

    @Override
    public DefaultResponse findUserAllCommentBoard(BoardLayer boardLayer) {
        Long rootId = boardCommentDao.findRootComment(boardLayer.getId()); // 루트 댓글 조회
        List<BoardQ> comments = boardCommentTbMapper.findUserAllCommentBoard(rootId, boardLayer.getUserTb().getUserId()); // 댓글 리스트 조회
        return new FindUBCResponse(rootId, comments);
    }

    @Override
    public DefaultResponse findAnonymousParentCommentBoard(BoardLayer boardLayer) throws LgException {
        Page<BoardQ> comments = boardCommentDao.findAnonymousParentCommentBoard(boardLayer, PageRequest.of(boardLayer.getPage(), boardLayer.getPageNum())); // 댓글 리스트 조회
        return new FindAPCBResponse(comments);
    }

    @Override
    public DefaultResponse findUserParentCommentBoard(BoardLayer boardLayer) throws LgException {
        Page<BoardQ> comments = boardCommentDao.findUserParentCommentBoard(boardLayer, PageRequest.of(boardLayer.getPage(), boardLayer.getPageNum())); // 댓글 리스트 조회
        return new FindUPCBResponse(comments);
    }

    @Override
    public DefaultResponse findAnonymousChildrenCommentBoard(BoardLayer boardLayer) throws LgException {
        Page<BoardQ> comments = boardCommentDao.findAnonymousChildrenCommentBoard(boardLayer, PageRequest.of(boardLayer.getPage(), boardLayer.getPageNum())); // 댓글 리스트 조회
        return new FindACCBResponse(comments);
    }

    @Override
    public DefaultResponse findUserChildrenCommentBoard(BoardLayer boardLayer) throws LgException {
        Page<BoardQ> comments = boardCommentDao.findUserChildrenCommentBoard(boardLayer, PageRequest.of(boardLayer.getPage(), boardLayer.getPageNum())); // 댓글 리스트 조회
        return new FindUCCBResponse(comments);
    }
}
