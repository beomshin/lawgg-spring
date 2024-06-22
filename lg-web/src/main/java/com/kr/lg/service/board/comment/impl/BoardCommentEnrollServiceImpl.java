package com.kr.lg.service.board.comment.impl;

import com.kr.lg.entities.BoardCommentTb;
import com.kr.lg.entities.BoardTb;
import com.kr.lg.model.web.common.listener.AlertBCEvent;
import com.kr.lg.model.web.common.listener.AlertBEvent;
import com.kr.lg.model.web.common.listener.BoardCEvent;
import com.kr.lg.model.web.common.listener.CommnetCNTEvent;
import com.kr.lg.repositories.BoardCommentRepository;
import com.kr.lg.model.web.common.layer.BoardLayer;
import com.kr.lg.service.board.comment.BoardCommentEnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardCommentEnrollServiceImpl implements BoardCommentEnrollService {

    private final BoardCommentRepository boardCommentRepository;
    private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void enrollUserCommentBoard(BoardLayer boardLayer) {
        boardCommentRepository.save(BoardCommentTb.builder()
                .userTb(boardLayer.getUserTb())
                .boardTb(BoardTb.builder().boardId(boardLayer.getId()).build())
                .parentId(boardLayer.getParentId())
                .depth(boardLayer.getDepth())
                .id(boardLayer.getLoginId())
                .password(boardLayer.getPassword())
                .writer(boardLayer.getWriter())
                .content(boardLayer.getContent())
                .emoticon(boardLayer.getEmoticon())
                .ip(boardLayer.getIp())
                .build()); // 멤버 댓글, 대댓글 등록
        applicationEventPublisher.publishEvent(new CommnetCNTEvent(boardLayer.getUserTb(), 1)); // 유저 댓글 개수 증가
        this.enrollCommentEvent(boardLayer);
    }

    @Override
    public void enrollAnonymousCommentBoard(BoardLayer boardLayer) {
        boardLayer.setPassword(encoder.encode(boardLayer.getPassword())); // 패스워드 암호화
        boardCommentRepository.save(BoardCommentTb.builder()
                .userTb(boardLayer.getUserTb())
                .boardTb(BoardTb.builder().boardId(boardLayer.getId()).build())
                .parentId(boardLayer.getParentId())
                .depth(boardLayer.getDepth())
                .id(boardLayer.getLoginId())
                .password(boardLayer.getPassword())
                .writer(boardLayer.getWriter())
                .content(boardLayer.getContent())
                .emoticon(boardLayer.getEmoticon())
                .ip(boardLayer.getIp())
                .build()); // 익명 댓글, 대댓글 등록
        this.enrollCommentEvent(boardLayer);
    }

    public void enrollCommentEvent(BoardLayer boardLayer) {
        applicationEventPublisher.publishEvent(new BoardCEvent(boardLayer.getId(), 1)); // 게시판 댓글 수 증가
        switch (boardLayer.getDepth()) {
            case PARENT_COMMENT:  applicationEventPublisher.publishEvent(new AlertBEvent(boardLayer)); break; // 게시판 작성자 알림
            case CHILDREN_COMMENT: applicationEventPublisher.publishEvent(new AlertBCEvent(boardLayer)); break; // 게시판 댓글 작성자 알림
        }
    }
}
