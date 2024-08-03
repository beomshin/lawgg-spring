package com.kr.lg.module.comment.service.impl;

import com.kr.lg.common.enums.entity.status.CommentStatus;
import com.kr.lg.db.repositories.BoardCommentRepository;
import com.kr.lg.db.repositories.TrialCommentRepository;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.exception.CommentResultCode;
import com.kr.lg.module.comment.service.CommentDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentDeleteServiceImpl implements CommentDeleteService {

    private final BoardCommentRepository boardCommentRepository;
    private final TrialCommentRepository trialCommentRepository;

    @Override
    @Transactional
    public void deleteBoardComment(long boardCommentId) throws CommentException {
        try {
            log.info("▶ [포지션 게시판 댓글] 댓글 삭제");
            boardCommentRepository.updateBoardCommentStatus(boardCommentId, CommentStatus.DELETE_STATUS);
        } catch (Exception e) {
            log.error("", e);
            throw new CommentException(CommentResultCode.FAIL_DELETE_COMMENT);
        }
    }


    @Override
    @Transactional
    public void deleteTrialComment(long trialCommentId) throws CommentException {
        try {
            log.info("▶ [트라이얼 댓글] 댓글 삭제");
            trialCommentRepository.updateTrialCommentStatus(trialCommentId, CommentStatus.DELETE_STATUS);
        } catch (Exception e) {
            log.error("", e);
            throw new CommentException(CommentResultCode.FAIL_DELETE_COMMENT);
        }
    }
}
