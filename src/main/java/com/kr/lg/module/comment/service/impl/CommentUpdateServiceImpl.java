package com.kr.lg.module.comment.service.impl;

import com.kr.lg.db.repositories.BoardCommentRepository;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.exception.CommentResultCode;
import com.kr.lg.module.comment.model.dto.CommentUpdateDto;
import com.kr.lg.module.comment.service.CommentUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentUpdateServiceImpl implements CommentUpdateService {

    private final BoardCommentRepository boardCommentRepository;

    @Override
    @Transactional
    public void updateBoardComment(CommentUpdateDto commentUpdateDto) throws CommentException {
        try {
            log.info("▶ [포지션 게시판] 댓글 수정");
            boardCommentRepository.updateBoardComment(commentUpdateDto.getBoardCommentId(), commentUpdateDto.getContent());
        } catch (Exception e) {
            throw new CommentException(CommentResultCode.FAIL_UPDATE_COMMENT);
        }
    }

    @Override
    @Transactional
    public void reportBoardComment(long boardCommentId) throws CommentException {
        try {
            log.info("▶ [포지션 게시판] 댓글 신고");
            boardCommentRepository.reportBoardComment(boardCommentId); // 신고
        } catch (Exception e) {
            throw new CommentException(CommentResultCode.FAIL_REPORT_COMMENT);
        }
    }

}
