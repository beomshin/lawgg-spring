package com.kr.lg.module.comment.service.impl;

import com.kr.lg.db.entities.BoardCommentTb;
import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.BoardCommentRepository;
import com.kr.lg.enums.StatusEnum;
import com.kr.lg.module.board.model.req.DeleteBoardCommentNotWithLoginRequest;
import com.kr.lg.module.board.model.req.DeleteBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.model.req.ReportBoardCommentRequest;
import com.kr.lg.module.comment.model.req.UpdateBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.model.req.UpdateBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.exception.CommentResultCode;
import com.kr.lg.module.comment.model.dto.*;
import com.kr.lg.module.comment.model.req.EnrollBoardCommentNotWithLoginRequest;
import com.kr.lg.module.comment.model.req.EnrollBoardCommentWithLoginRequest;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.service.CommentDeleteService;
import com.kr.lg.module.comment.service.CommentEnrollService;
import com.kr.lg.module.comment.service.CommentService;
import com.kr.lg.module.comment.service.CommentUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentEnrollService commentEnrollService;
    private final CommentUpdateService commentUpdateService;
    private final CommentDeleteService commentDeleteService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final BoardCommentRepository boardCommentRepository;;

    private final BCryptPasswordEncoder encoder;

    @Override
    public void enrollBoardCommentNotWithLogin(EnrollBoardCommentNotWithLoginRequest request, String ip) throws CommentException {
        log.info("▶ [댓글] enrollBoardCommentNotWithLogin 메소드 실행");

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
        log.info("▶ [댓글] enrollBoardCommentWithLogin 메소드 실행");

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

    @Override
    @Transactional
    public void updateBoardCommentNotWithLogin(UpdateBoardCommentNotWithLoginRequest request) throws CommentException {
        log.info("▶ [댓글] updateBoardCommentNotWithLogin 메소드 실행");

        Optional<BoardCommentTb> boardCommentTb = boardCommentRepository.findById(request.getId());
        if (boardCommentTb.isPresent()) {
            if (!encoder.matches(request.getPassword(), boardCommentTb.get().getPassword())) throw new CommentException(CommentResultCode.UN_MATCH_PASSWORD);
            commentUpdateService.updateBoardComment(CommentUpdateDto.builder()
                            .boardCommentId(request.getId())
                            .content(request.getContent())
                    .build());
        }
    }

    @Override
    @Transactional
    public void updateBoardCommentWithLogin(UpdateBoardCommentWithLoginRequest request, UserTb userTb) throws CommentException {
        log.info("▶ [댓글] updateBoardCommentWithLogin 메소드 실행");

        Optional<BoardCommentTb> boardCommentTb = boardCommentRepository.findById(request.getId());
        if (boardCommentTb.isPresent()) {
            if (!encoder.matches(request.getPassword(), boardCommentTb.get().getPassword())) throw new CommentException(CommentResultCode.UN_MATCH_PASSWORD);
            else if (!userTb.getUserId().equals(boardCommentTb.get().getUserTb().getUserId())) throw new CommentException(CommentResultCode.UN_MATCHED_USER);
            commentUpdateService.updateBoardComment(CommentUpdateDto.builder()
                    .boardCommentId(request.getId())
                    .content(request.getContent())
                    .build());
        } else {
            throw new CommentException(CommentResultCode.FAIL_FIND_BOARD_COMMENT);
        }
    }

    @Override
    @Transactional
    public void reportBoardComment(ReportBoardCommentRequest request) throws CommentException {
        log.info("▶ [댓글] reportBoardComment 메소드 실행");

        Optional<BoardCommentTb> boardCommentTb = boardCommentRepository.findById(request.getId());
        if (boardCommentTb.isPresent()) {
            commentUpdateService.reportBoardComment(request.getId());
        } else {
            throw new CommentException(CommentResultCode.FAIL_FIND_BOARD_COMMENT);
        }
    }

    @Override
    @Transactional
    public void deleteBoardCommentNotWithLogin(DeleteBoardCommentNotWithLoginRequest request) throws CommentException {
        log.info("▶ [댓글] deleteBoardCommentNotWithLogin 메소드 실행");

        Optional<BoardCommentTb> boardCommentTb = boardCommentRepository.findById(request.getId());
        if (boardCommentTb.isPresent()) {
            if (boardCommentTb.get().getStatus().equals(StatusEnum.DELETE_STATUS)) throw new CommentException(CommentResultCode.ALREADY_DELETE_BOARD_COMMENT);
            else if (!encoder.matches(request.getPassword(), boardCommentTb.get().getPassword())) throw new CommentException(CommentResultCode.UN_MATCH_PASSWORD);
            commentDeleteService.deleteBoardComment(request.getId());
            applicationEventPublisher.publishEvent(new BoardCommentCreateCountEvent(boardCommentTb.get().getBoardTb().getBoardId(), -1));

        } else {
            throw new CommentException(CommentResultCode.FAIL_FIND_BOARD_COMMENT);
        }
    }

    @Override
    @Transactional
    public void deleteBoardCommentWithLogin(DeleteBoardCommentWithLoginRequest request, UserTb userTb) throws CommentException {
        log.info("▶ [댓글] deleteBoardCommentWithLogin 메소드 실행");

        Optional<BoardCommentTb> boardCommentTb = boardCommentRepository.findById(request.getId());
        if (boardCommentTb.isPresent()) {
            if (boardCommentTb.get().getStatus().equals(StatusEnum.DELETE_STATUS)) throw new CommentException(CommentResultCode.ALREADY_DELETE_BOARD_COMMENT);
            else if (!userTb.getUserId().equals(boardCommentTb.get().getUserTb().getUserId())) throw new CommentException(CommentResultCode.UN_MATCHED_USER);
            commentDeleteService.deleteBoardComment(request.getId());
            applicationEventPublisher.publishEvent(new BoardCommentCreateCountEvent(boardCommentTb.get().getBoardTb().getBoardId(), -1));
            applicationEventPublisher.publishEvent(new UserCommentCreateCountEvent(userTb, -1)); // 댓글 개수 감소
        } else {
            throw new CommentException(CommentResultCode.FAIL_FIND_BOARD_COMMENT);
        }
    }
}
