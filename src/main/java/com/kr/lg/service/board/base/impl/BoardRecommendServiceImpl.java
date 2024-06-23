package com.kr.lg.service.board.base.impl;

import com.kr.lg.entities.BoardRecommendTb;
import com.kr.lg.entities.BoardTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.web.common.listener.BoardREvent;
import com.kr.lg.repositories.BoardRecommendRepository;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.web.common.layer.BoardLayer;
import com.kr.lg.service.board.base.BoardRecommendService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardRecommendServiceImpl implements BoardRecommendService {

    private final BoardRecommendRepository boardRecommendRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void recommendBoard(BoardLayer boardLayer) throws LgException {
        Optional<BoardRecommendTb> boardRecommendTb = boardRecommendRepository.findByBoardTb_BoardIdAndUserTb(boardLayer.getId(), boardLayer.getUserTb()); // 추천 내역 조회
        if (boardRecommendTb.isPresent()) throw new LgException(GlobalCode.ALREADY_RECOMMEND_BOARD); // 이미 추천 처리
        applicationEventPublisher.publishEvent(new BoardREvent(boardLayer.getId(), 1)); // 추천 수 증가
        boardRecommendRepository.save(new BoardRecommendTb(boardLayer.getId(), boardLayer.getUserTb())); // 추천
    }

    @Override
    public int deleteRecommendBoard(BoardLayer boardLayer)  {
        return boardRecommendRepository.deleteRecommendBoard(BoardTb.builder().boardId(boardLayer.getId()).build(), boardLayer.getUserTb());
    }

}
