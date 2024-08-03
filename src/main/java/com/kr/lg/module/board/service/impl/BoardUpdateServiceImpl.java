package com.kr.lg.module.board.service.impl;

import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.board.model.dto.BoardUpdateDto;
import com.kr.lg.module.board.service.BoardUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardUpdateServiceImpl implements BoardUpdateService {

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public void updateBoard(BoardUpdateDto boardUpdateDto) throws BoardException {
        try {
            log.info("▶ [포지션 게시판] 포지션 게시판 업데이트");
            boardRepository.updateBoard(boardUpdateDto.getBoardId(), boardUpdateDto.getTitle(), boardUpdateDto.getContent()); // 게시판 업데이트
        } catch (Exception e) {
            log.error("", e);
            throw new BoardException(BoardResultCode.FAIL_UPDATE_BOARD);
        }
    }

}
