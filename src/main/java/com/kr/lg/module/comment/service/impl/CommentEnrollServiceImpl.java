package com.kr.lg.module.comment.service.impl;

import com.kr.lg.db.entities.BoardCommentTb;
import com.kr.lg.db.entities.TrialCommentTb;
import com.kr.lg.db.repositories.BoardCommentRepository;
import com.kr.lg.db.repositories.TrialCommentRepository;
import com.kr.lg.module.comment.exception.CommentException;
import com.kr.lg.module.comment.exception.CommentResultCode;
import com.kr.lg.module.comment.model.dto.CommentEnrollDto;
import com.kr.lg.module.comment.service.CommentEnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentEnrollServiceImpl implements CommentEnrollService {

    private final BoardCommentRepository boardCommentRepository;
    private final TrialCommentRepository trialCommentRepository;
    private final BCryptPasswordEncoder encoder;


    @Override
    @Transactional
    public void enrollBoardComment(CommentEnrollDto commentEnrollDto) throws CommentException {
        try {
            log.info("▶ [포지션 게시판 댓글] 댓글 등록");
            BoardCommentTb boardCommentTb = BoardCommentTb.builder()
                    .userTb(commentEnrollDto.getUserTb())
                    .boardTb(commentEnrollDto.getBoardTb())
                    .parentId(commentEnrollDto.getParentId())
                    .depth(commentEnrollDto.getDepth())
                    .id(commentEnrollDto.getLoginId())
                    .password(StringUtils.isBlank(commentEnrollDto.getPassword()) ? null : encoder.encode(commentEnrollDto.getPassword()))
                    .writer(commentEnrollDto.getWriter())
                    .content(commentEnrollDto.getContent())
                    .emoticon(commentEnrollDto.getEmoticon())
                    .ip(commentEnrollDto.getIp())
                    .build();
            boardCommentRepository.save(boardCommentTb);
        } catch (Exception e) {
            log.error("", e);
            throw new CommentException(CommentResultCode.FAIL_ENROLL_COMMENT);
        }
    }

    @Override
    @Transactional
    public void enrollTrialComment(CommentEnrollDto commentEnrollDto) throws CommentException {
        try {
            log.info("▶ [트라이얼 댓글] 댓글 등록");
            TrialCommentTb boardCommentTb = TrialCommentTb.builder()
                    .userTb(commentEnrollDto.getUserTb())
                    .trialTb(commentEnrollDto.getTrialTb())
                    .parentId(commentEnrollDto.getParentId())
                    .depth(commentEnrollDto.getDepth())
                    .id(commentEnrollDto.getLoginId())
                    .password(StringUtils.isBlank(commentEnrollDto.getPassword()) ? null : encoder.encode(commentEnrollDto.getPassword()))
                    .writer(commentEnrollDto.getWriter())
                    .content(commentEnrollDto.getContent())
                    .emoticon(commentEnrollDto.getEmoticon())
                    .ip(commentEnrollDto.getIp())
                    .build();
            trialCommentRepository.save(boardCommentTb);
        } catch (Exception e) {
            log.error("", e);
            throw new CommentException(CommentResultCode.FAIL_ENROLL_COMMENT);
        }
    }

}
