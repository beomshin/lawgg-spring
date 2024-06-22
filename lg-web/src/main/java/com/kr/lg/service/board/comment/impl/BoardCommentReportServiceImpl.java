package com.kr.lg.service.board.comment.impl;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.repositories.BoardCommentRepository;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.model.web.common.layer.BoardLayer;
import com.kr.lg.service.board.comment.BoardCommentReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
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
