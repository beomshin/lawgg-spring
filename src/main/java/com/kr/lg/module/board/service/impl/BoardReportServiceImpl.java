package com.kr.lg.module.board.service.impl;

import com.kr.lg.db.entities.ReportTb;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.db.repositories.ReportRepository;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.board.model.dto.BoardReportDto;
import com.kr.lg.module.board.service.BoardReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardReportServiceImpl implements BoardReportService {

    private final BoardRepository boardRepository;
    private final ReportRepository reportRepository;


    @Override
    public void reportBoard(BoardReportDto boardReportDto) throws BoardException {
        try {
            log.info("▶ [포지션 게시판] 포지션 게시판 신고");
            ReportTb reportTb = ReportTb.builder()
                    .ip(boardReportDto.getIp())
                    .content(boardReportDto.getContent())
                    .boardTb(boardReportDto.getBoardTb())
                    .build();
            reportRepository.save(reportTb);
            boardRepository.reportBoard(boardReportDto.getBoardTb().getBoardId());
        } catch (Exception e) {
            throw new BoardException(BoardResultCode.FAIL_REPORT_BOARD);
        }
    }

}
