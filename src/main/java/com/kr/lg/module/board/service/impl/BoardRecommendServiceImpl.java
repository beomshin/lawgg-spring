package com.kr.lg.module.board.service.impl;

import com.kr.lg.db.entities.BoardRecommendTb;
import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.BoardRecommendRepository;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.board.service.BoardRecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardRecommendServiceImpl implements BoardRecommendService {

    private final BoardRecommendRepository boardRecommendRepository;

    @Override
    @Transactional
    public void recommendBoard(BoardTb boardTb, UserTb userTb) throws BoardException {
        try {
            log.info("▶ [포지션 게시판] 포지션 게시판 추천");
            BoardRecommendTb boardRecommendTb = BoardRecommendTb.builder()
                    .boardTb(boardTb)
                    .userTb(userTb)
                    .build();
            boardRecommendRepository.save(boardRecommendTb); // 추천
        } catch (Exception e) {
            log.error("", e);
            throw new BoardException(BoardResultCode.FAIL_RECOMMEND_BOARD);
        }
    }

}
