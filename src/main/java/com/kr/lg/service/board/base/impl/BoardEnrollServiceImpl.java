package com.kr.lg.service.board.base.impl;

import com.kr.lg.exception.LgException;
import com.kr.lg.db.dao.BoardDao;
import com.kr.lg.model.common.listener.BoardCNTEvent;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.service.board.base.BoardEnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardEnrollServiceImpl implements BoardEnrollService {

    private final BoardDao boardDao;
    private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public boolean enrollUserBoard(BoardLayer boardLayer) throws LgException {
        if (boardLayer.getUserTb() == null) throw new LgException(GlobalCode.NOT_EXIST_USER); // 유저가 아닌경우
        boolean result = boardDao.saveBoard(boardLayer) != null ? true : false; // 게시판 저장
        if (result) {
            applicationEventPublisher.publishEvent(new BoardCNTEvent(boardLayer.getUserTb(), 1));
        }
        return result;
    }

    @Override
    public boolean enrollAnonymousBoard(BoardLayer boardLayer) {
        boardLayer.setPassword(encoder.encode(boardLayer.getPassword())); // 비밀번호 암호화
        return boardDao.saveBoard(boardLayer) != null ? true : false; // 게시판 저장
    }

    @Override
    public boolean enrollLawFirmBoard(BoardLayer boardLayer) throws LgException {
        if (boardLayer.getUserTb().getLawFirmId() == null) throw new LgException(GlobalCode.NOT_EXIST_LAW_FIRM); // 로펌 검증
        else if (boardLayer.getUserTb().getLawFirmId().getLawFirmId() != boardLayer.getId()) throw new LgException(GlobalCode.UN_MATCHED_LAW_FIRM_USER);
        boolean result = boardDao.saveBoard(boardLayer) != null ? true : false; // 게시판 저장
        if (result) {
            applicationEventPublisher.publishEvent(new BoardCNTEvent(boardLayer.getUserTb(), 1));
        }
        return result;
    }

}
