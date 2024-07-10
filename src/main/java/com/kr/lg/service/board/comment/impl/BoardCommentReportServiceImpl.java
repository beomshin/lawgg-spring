package com.kr.lg.service.board.comment.impl;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.db.repositories.BoardCommentRepository;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.service.board.comment.BoardCommentReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardCommentReportServiceImpl implements BoardCommentReportService {

    private final BoardCommentRepository boardCommentRepository;

    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public int reportCommentBoard(BoardLayer boardLayer) throws LgException {
        return boardCommentRepository.reportBoardComment(boardLayer.getId()); // 신고
    }

}
