package com.kr.lg.service.board.base.impl;

import com.kr.lg.entities.BoardTb;
import com.kr.lg.entities.ReportTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.repositories.BoardRepository;
import com.kr.lg.repositories.ReportRepository;
import com.kr.lg.model.web.common.global.GlobalCode;
import com.kr.lg.model.web.common.layer.BoardLayer;
import com.kr.lg.service.board.base.BoardReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardReportServiceImpl implements BoardReportService {

    private final BoardRepository boardRepository;
    private final ReportRepository reportRepository;

    @Value("${spring.profiles.active}")
    private String active;


    @Override
    public int reportBoard(BoardLayer boardLayer) throws LgException {
        if (active.equals("local")) {
            reportRepository.save(ReportTb.builder()
                    .ip(boardLayer.getIp())
                    .content(boardLayer.getContent())
                    .boardTb(BoardTb.builder().boardId(boardLayer.getId()).build())
                    .build());
            return 1;
        }
        reportRepository.save(ReportTb.builder()
                .ip(boardLayer.getIp())
                .content(boardLayer.getContent())
                .boardTb(BoardTb.builder().boardId(boardLayer.getId()).build())
                .build());
        return boardRepository.reportBoard(boardLayer.getId()); // 신고
    }

}
