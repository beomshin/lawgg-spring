package com.kr.lg.module.comment.service.impl;

import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.comment.model.dto.*;
import com.kr.lg.module.comment.model.req.EnrollBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.model.req.EnrollBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.service.CommentEnrollService;
import com.kr.lg.module.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentEnrollService commentEnrollService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void enrollBoardCommentNotWithLogin(EnrollBoardCommentNotWithLoginRequest request, String ip) throws CommentException {
        CommentEnrollDto enrollDto = CommentEnrollDto.builder()
                .id(request.getId())
                .boardTb(BoardTb.builder().boardId(request.getId()).build())
                .parentId(request.getParentId())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .writer(request.getLoginId())
                .content(request.getContent())
                .depth(request.getDepth())
                .emoticon(request.getEmoticon())
                .ip(ip)
                .boardCommentId(request.getBoardCommentId())
                .build();
        commentEnrollService.enrollBoardComment(enrollDto); // 댓글 등록
        applicationEventPublisher.publishEvent(new BoardCommentCreateCountEvent(enrollDto.getId(), 1)); // 게시판 댓글 수 증가
        switch (enrollDto.getDepth()) {
            case PARENT_COMMENT:  applicationEventPublisher.publishEvent(new CommentCreateAlertToBoardWriterEvent(enrollDto)); break; // 게시판 작성자 알림
            case CHILDREN_COMMENT: applicationEventPublisher.publishEvent(new CommentCreateAlertToWriterEvent(enrollDto)); break; // 게시판 댓글 작성자 알림
        }
    }

    @Override
    public void enrollBoardCommentWithLogin(EnrollBoardCommentWithLoginRequest request, UserTb userTb, String ip) throws CommentException {
        CommentEnrollDto enrollDto = CommentEnrollDto.builder()
                .id(request.getId())
                .boardTb(BoardTb.builder().boardId(request.getId()).build())
                .parentId(request.getParentId())
                .loginId(userTb.getLoginId())
                .writer(userTb.getNickName())
                .content(request.getContent())
                .depth(request.getDepth())
                .userTb(userTb)
                .emoticon(request.getEmoticon())
                .ip(ip)
                .boardCommentId(request.getBoardCommentId())
                .build();
        commentEnrollService.enrollBoardComment(enrollDto); // 댓글 등록
        applicationEventPublisher.publishEvent(new UserCommentCreateCountEvent(userTb, 1)); // 유저 댓글 개수 증가
        applicationEventPublisher.publishEvent(new BoardCommentCreateCountEvent(enrollDto.getId(), 1)); // 게시판 댓글 수 증가
        switch (enrollDto.getDepth()) {
            case PARENT_COMMENT:  applicationEventPublisher.publishEvent(new CommentCreateAlertToBoardWriterEvent(enrollDto)); break; // 게시판 작성자 알림
            case CHILDREN_COMMENT: applicationEventPublisher.publishEvent(new CommentCreateAlertToWriterEvent(enrollDto)); break; // 게시판 댓글 작성자 알림
        }
    }
}
