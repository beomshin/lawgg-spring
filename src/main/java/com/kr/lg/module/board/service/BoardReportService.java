package com.kr.lg.module.board.service;

import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.dto.BoardReportDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardReportService {

    void reportBoard(BoardReportDto boardReportDto) throws BoardException;
}
