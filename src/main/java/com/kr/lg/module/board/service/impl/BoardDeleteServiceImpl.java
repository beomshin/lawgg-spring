package com.kr.lg.module.board.service.impl;

import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.board.service.BoardDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardDeleteServiceImpl implements BoardDeleteService {

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public void deleteBoard(long boardId) throws BoardException {
        try {
            log.info("▶ [포지션 게시판] 포지션 게시판 삭제");
            boardRepository.deleteBoard(boardId); // 게시판 삭제
        } catch (Exception e) {
            throw new BoardException(BoardResultCode.FAIL_DELETE_BOARD);
        }
    }

}
