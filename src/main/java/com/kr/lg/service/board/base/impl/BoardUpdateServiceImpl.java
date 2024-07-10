package com.kr.lg.service.board.base.impl;

import com.kr.lg.db.dao.BoardDao;
import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.exception.LgException;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.common.utils.BoardUtils;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.service.board.base.BoardUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardUpdateServiceImpl implements BoardUpdateService {

    private final BoardRepository boardRepository;
    private final BoardDao boardDao;
    private final BoardUtils boardUtils;

    @Override
    public int updateUserBoard(BoardLayer boardLayer) throws LgException {
        BoardTb boardTb = boardRepository.findById(boardLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD)); // 게시판 조회
        boardUtils.isRightUserNonePassword(boardLayer.getUserTb(), boardTb.getUserTb(), boardTb.getWriterType()); // 유저 검사
        return boardDao.updateBoard(boardLayer);
    }
    @Override
    public int updateAnonymousBoard(BoardLayer boardLayer) throws LgException {
        BoardTb boardTb = boardRepository.findById(boardLayer.getId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_BOARD)); // 게시판 조회
        boardUtils.isRightAnonymousPassword(boardLayer.getPassword(), boardTb.getPassword(), boardTb.getWriterType()); // 비밀번호 검사
        return boardDao.updateBoard(boardLayer);
    }

}
