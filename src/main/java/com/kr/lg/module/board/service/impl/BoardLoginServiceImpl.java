package com.kr.lg.module.board.service.impl;

import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.web.dto.global.GlobalCode;
import com.kr.lg.common.utils.BoardUtils;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.module.board.service.BoardLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardLoginServiceImpl implements BoardLoginService {

    private final BoardRepository boardRepository;
    private final BoardUtils boardUtils;

    @Override
    public void loginAnonymousBoard(BoardLayer boardLayer) throws LgException {
        BoardTb boardTb = boardRepository.findById(boardLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD)); // 게시판 조회
        boardUtils.isRightAnonymousPassword(boardLayer.getPassword(), boardTb.getPassword(), boardTb.getWriterType()); // 비회원 유저 패스워드 검사
    }

    @Override
    public void loginUserBoard(BoardLayer boardLayer) throws LgException {
        BoardTb boardTb = boardRepository.findById(boardLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD)); // 게시판 조회
        boardUtils.isRightUserNonePassword(boardLayer.getUserTb(), boardTb.getUserTb(), boardTb.getWriterType()); // 회원 유저 패스워드 검사
    }
}
