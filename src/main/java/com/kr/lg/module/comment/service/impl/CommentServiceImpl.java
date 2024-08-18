package com.kr.lg.module.comment.service.impl;

import com.kr.lg.common.enums.entity.level.CommentDepthLevel;
import com.kr.lg.common.enums.entity.status.CommentStatus;
import com.kr.lg.common.enums.entity.type.WriterType;
import com.kr.lg.db.entities.*;
import com.kr.lg.db.repositories.BoardCommentRepository;
import com.kr.lg.db.repositories.TrialCommentRepository;
import com.kr.lg.module.comment.model.event.TrialCommentCreateAlertToWriterEvent;
import com.kr.lg.module.comment.model.event.TrialCommentCreateAlertToTrialWriterEvent;
import com.kr.lg.module.comment.model.event.TrialCommentCreateCountEvent;
import com.kr.lg.module.comment.model.req.DeleteTrialCommentRequest;
import com.kr.lg.module.comment.model.req.EnrollTrialCommentRequest;
import com.kr.lg.module.comment.model.req.DeletePositionCommentRequest;
import com.kr.lg.module.comment.model.event.BoardCommentCreateCountEvent;
import com.kr.lg.module.comment.model.event.BoardCommentCreateAlertToBoardWriterEvent;
import com.kr.lg.module.comment.model.event.BoardCommentCreateAlertToWriterEvent;
import com.kr.lg.module.comment.model.event.UserCommentCreateCountEvent;
import com.kr.lg.module.comment.exception.CommentResultCode;
import com.kr.lg.module.comment.model.dto.*;
import com.kr.lg.module.comment.model.req.EnrollPositionCommentRequest;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.service.CommentDeleteService;
import com.kr.lg.module.comment.service.CommentEnrollService;
import com.kr.lg.module.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentEnrollService commentEnrollService;
    private final CommentDeleteService commentDeleteService;
    private final ApplicationEventPublisher applicationEventPublisher;

    private final BoardCommentRepository boardCommentRepository;
    private final TrialCommentRepository trialCommentRepository;

    private final BCryptPasswordEncoder encoder;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void enrollBoardCommentNotWithLogin(EnrollPositionCommentRequest request, String ip) throws CommentException {
        Optional<BoardCommentTb> commentTb = boardCommentRepository.findByBoardTb_BoardIdAndDepth(request.getBoardId(), CommentDepthLevel.ROOT_COMMENT);
        if (! commentTb.isPresent()) {
            throw new CommentException(CommentResultCode.FAIL_FIND_BOARD);
        }

        CommentEnrollDto enrollDto = CommentEnrollDto.builder()
                .id(request.getBoardId())
                .boardTb(commentTb.get().getBoardTb())
                .parentId(commentTb.get().getBoardCommentId())
                .loginId(request.getLoginId())
                .password(request.getPassword())
                .writer(request.getLoginId())
                .content(request.getContent())
                .depth(CommentDepthLevel.of(request.getDepth()))
                .ip(ip)
                .build();
        commentEnrollService.enrollBoardComment(enrollDto); // 댓글 등록
        applicationEventPublisher.publishEvent(new BoardCommentCreateCountEvent(enrollDto.getId(), 1)); // 게시판 댓글 수 증가
        switch (enrollDto.getDepth()) {
            case PARENT_COMMENT:  applicationEventPublisher.publishEvent(new BoardCommentCreateAlertToBoardWriterEvent(enrollDto)); break; // 게시판 작성자 알림
            case CHILDREN_COMMENT: applicationEventPublisher.publishEvent(new BoardCommentCreateAlertToWriterEvent(enrollDto)); break; // 게시판 댓글 작성자 알림
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void enrollBoardCommentWithLogin(EnrollPositionCommentRequest request, UserTb userTb, String ip) throws CommentException {
        Optional<BoardCommentTb> commentTb = boardCommentRepository.findByBoardTb_BoardIdAndDepth(request.getBoardId(), CommentDepthLevel.ROOT_COMMENT);
        if (! commentTb.isPresent()) {
            throw new CommentException(CommentResultCode.FAIL_FIND_BOARD);
        }

        CommentEnrollDto enrollDto = CommentEnrollDto.builder()
                .id(request.getBoardId())
                .boardTb(commentTb.get().getBoardTb())
                .parentId(commentTb.get().getBoardCommentId())
                .loginId(userTb.getLoginId())
                .writer(userTb.getNickName())
                .content(request.getContent())
                .depth(CommentDepthLevel.of(request.getDepth()))
                .userTb(userTb)
                .ip(ip)
                .build();
        commentEnrollService.enrollBoardComment(enrollDto); // 댓글 등록
        applicationEventPublisher.publishEvent(new UserCommentCreateCountEvent(userTb, 1)); // 유저 댓글 개수 증가
        applicationEventPublisher.publishEvent(new BoardCommentCreateCountEvent(enrollDto.getId(), 1)); // 게시판 댓글 수 증가
        switch (enrollDto.getDepth()) {
            case PARENT_COMMENT:  applicationEventPublisher.publishEvent(new BoardCommentCreateAlertToBoardWriterEvent(enrollDto)); break; // 게시판 작성자 알림
            case CHILDREN_COMMENT: applicationEventPublisher.publishEvent(new BoardCommentCreateAlertToWriterEvent(enrollDto)); break; // 게시판 댓글 작성자 알림
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBoardCommentNotWithLogin(DeletePositionCommentRequest request) throws CommentException {
        Optional<BoardCommentTb> boardCommentTb = boardCommentRepository.findByBoardCommentId(request.getCommentId());
        if (boardCommentTb.isPresent() && boardCommentTb.get().getBoardTb().getWriterType() == WriterType.ANONYMOUS_TYPE) {
            if (boardCommentTb.get().getStatus().equals(CommentStatus.DELETE_STATUS)) throw new CommentException(CommentResultCode.ALREADY_DELETE_BOARD_COMMENT);
            else if (!encoder.matches(request.getPassword(), boardCommentTb.get().getPassword())) throw new CommentException(CommentResultCode.UN_MATCH_PASSWORD);
            commentDeleteService.deleteBoardComment(request.getCommentId());
            applicationEventPublisher.publishEvent(new BoardCommentCreateCountEvent(boardCommentTb.get().getBoardTb().getBoardId(), -1));

        } else {
            throw new CommentException(CommentResultCode.FAIL_FIND_BOARD_COMMENT);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBoardCommentWithLogin(DeletePositionCommentRequest request, UserTb userTb) throws CommentException {
        Optional<BoardCommentTb> boardCommentTb = boardCommentRepository.findByBoardCommentId(request.getCommentId());
        if (boardCommentTb.isPresent() && boardCommentTb.get().getBoardTb().getWriterType() == WriterType.MEMBER_TYPE) {
            if (boardCommentTb.get().getStatus().equals(CommentStatus.DELETE_STATUS)) throw new CommentException(CommentResultCode.ALREADY_DELETE_BOARD_COMMENT);
            else if (!userTb.getUserId().equals(boardCommentTb.get().getUserTb().getUserId())) throw new CommentException(CommentResultCode.UN_MATCHED_USER);
            commentDeleteService.deleteBoardComment(request.getCommentId());
            applicationEventPublisher.publishEvent(new BoardCommentCreateCountEvent(boardCommentTb.get().getBoardTb().getBoardId(), -1));
            applicationEventPublisher.publishEvent(new UserCommentCreateCountEvent(userTb, -1)); // 댓글 개수 감소
        } else {
            throw new CommentException(CommentResultCode.FAIL_FIND_BOARD_COMMENT);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void enrollTrialCommentWithLogin(EnrollTrialCommentRequest request, UserTb userTb, String ip) throws CommentException {
        Optional<TrialCommentTb> commentTb = trialCommentRepository.findByTrialTb_TrialIdAndDepth(request.getTrialId(), CommentDepthLevel.ROOT_COMMENT);
        if (! commentTb.isPresent()) {
            throw new CommentException(CommentResultCode.FAIL_FIND_TRIAL);
        }

        CommentEnrollDto enrollDto = CommentEnrollDto.builder()
                .id(request.getTrialId())
                .loginId(userTb.getLoginId())
                .userTb(userTb)
                .trialTb(commentTb.get().getTrialTb())
                .parentId(commentTb.get().getTrialCommentId())
                .content(request.getContent())
                .depth(CommentDepthLevel.of(request.getDepth()))
                .writer(userTb.getNickName())
                .ip(ip)
                .build();

        commentEnrollService.enrollTrialComment(enrollDto);
        applicationEventPublisher.publishEvent(new TrialCommentCreateCountEvent(request.getTrialId(), 1)); // 트라이얼 답글 개수 증가
        applicationEventPublisher.publishEvent(new UserCommentCreateCountEvent(userTb, 1)); // 댓글 개수 증가
        switch (enrollDto.getDepth()) {
            case PARENT_COMMENT: applicationEventPublisher.publishEvent(new TrialCommentCreateAlertToTrialWriterEvent(enrollDto)); break;
            case CHILDREN_COMMENT: applicationEventPublisher.publishEvent(new TrialCommentCreateAlertToWriterEvent(enrollDto)); break;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteTrialCommentWithLogin(DeleteTrialCommentRequest request, UserTb userTb) throws CommentException {
        Optional<TrialCommentTb> trialCommentTb = trialCommentRepository.findById(request.getCommentId());
        if (trialCommentTb.isPresent()) {
            if (trialCommentTb.get().getStatus().equals(CommentStatus.DELETE_STATUS)) throw new CommentException(CommentResultCode.ALREADY_DELETE_TRIAL_COMMENT);
            else if (!userTb.getUserId().equals(trialCommentTb.get().getUserTb().getUserId())) throw new CommentException(CommentResultCode.UN_MATCHED_USER);
            commentDeleteService.deleteTrialComment(trialCommentTb.get().getTrialCommentId());
            applicationEventPublisher.publishEvent(new TrialCommentCreateCountEvent(trialCommentTb.get().getTrialTb().getTrialId(), -1)); // 트라이얼 답글 개수 감수
            applicationEventPublisher.publishEvent(new UserCommentCreateCountEvent(userTb, -1)); // 댓글 개수 감소
        } else {
            throw new CommentException(CommentResultCode.FAIL_FIND_TRIAL_COMMENT);
        }
    }
}
