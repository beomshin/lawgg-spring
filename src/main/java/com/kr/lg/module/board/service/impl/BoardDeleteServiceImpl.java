package com.kr.lg.module.board.service.impl;

import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.enums.PostEnum;
import com.kr.lg.enums.StatusEnum;
import com.kr.lg.model.common.listener.BoardCNTEvent;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.common.utils.BoardUtils;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.module.board.service.BoardDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardDeleteServiceImpl implements BoardDeleteService {

    private final BoardRepository boardRepository;
    private final BoardUtils boardUtils;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public int deleteAnonymousBoard(BoardLayer boardLayer) throws LgException {
        BoardTb boardTb = boardRepository.findById(boardLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD)); // 게시판 조회
        if (boardTb.getStatus().equals(StatusEnum.DELETE_STATUS)) throw new LgException(GlobalCode.ALREADY_DELETE_BOARD);
        boardUtils.isRightAnonymousPassword(boardLayer.getPassword(), boardTb.getPassword(), boardTb.getWriterType()); // 비 회원 유저 패스워드 검사
        return boardRepository.updateBoardStatus(boardLayer.getId(), StatusEnum.DELETE_STATUS); // 게시판 삭제
    }

    @Override
    public int deleteUserBoard(BoardLayer boardLayer) throws LgException {
        BoardTb boardTb = boardRepository.findById(boardLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD)); // 게시판 조회
        if (boardTb.getStatus().equals(StatusEnum.DELETE_STATUS)) throw new LgException(GlobalCode.ALREADY_DELETE_BOARD);
        if (boardTb.getPostType().equals(PostEnum.BEST_TYPE) || boardTb.getPostType().equals(PostEnum.RECOMMEND)) {
            boardUtils.isRightUserPassword(boardLayer.getUserTb(), boardTb.getUserTb(), boardLayer.getPassword(), boardTb.getWriterType()); // 회원 유저 패스워드 검사
        } else {
            boardUtils.isRightUserNonePassword(boardLayer.getUserTb(), boardTb.getUserTb(), boardTb.getWriterType()); // 회원 유저 패스워드 미검사
        }
        int result = boardRepository.updateBoardStatus(boardLayer.getId(), StatusEnum.DELETE_STATUS); // 게시판 삭제;
        if (result > 0) {
            applicationEventPublisher.publishEvent(new BoardCNTEvent(boardLayer.getUserTb(), -1));
        }
        return result;
    }

    @Override
    @Transactional
    public void deleteBoard(long boardId) throws BoardException {
        try {
            boardRepository.updateBoardStatus(boardId, StatusEnum.DELETE_STATUS);
        } catch (Exception e) {
            throw new BoardException(BoardResultCode.FAIL_DELETE_BOARD);
        }
    }

}
