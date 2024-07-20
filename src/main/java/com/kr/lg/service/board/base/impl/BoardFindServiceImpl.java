package com.kr.lg.service.board.base.impl;

import com.kr.lg.exception.LgException;
import com.kr.lg.db.dao.BoardDao;
import com.kr.lg.model.net.response.board.base.*;
import com.kr.lg.model.querydsl.BoardQ;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.service.board.base.BoardFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardFindServiceImpl implements BoardFindService {

    private final BoardDao boardDao;
    private final BoardRepository boardRepository;


//    @Override
//    public DefaultResponse findAllListBoard(BoardLayer boardLayer) {
//        Page<BoardQ> boards = boardDao.findAllBoardList(boardLayer, PageRequest.of(boardLayer.getPage(), boardLayer.getPageNum())); // 게시판 리스트 조회
//        return new FindBoardResponse(boards);
//    }

    @Override
    public DefaultResponse findUserListBoard(BoardLayer boardLayer) {
        Page<BoardQ> boards =boardDao.findUserBoardList(boardLayer, PageRequest.of(boardLayer.getPage(), boardLayer.getPageNum())); // 유저 게시판 리스트 조회
        return new FindUBLResponse(boards);
    }

    @Override
    public DefaultResponse findLawFirmListBoard(BoardLayer boardLayer) throws LgException {
        Page<BoardQ> boards =boardDao.findLawFirmBoardList(boardLayer, PageRequest.of(boardLayer.getPage(), boardLayer.getPageNum())); // 유저 게시판 리스트 조회
        return new FindLFLBDResponse(boards);
    }

    @Override
    public DefaultResponse findAnonymousDetailBoard(BoardLayer boardLayer) throws LgException {
        BoardQ board = boardDao.findBoardDetail(boardLayer); // 게시판 상세 조회
        return new FindABDResponse(board);
    }

    @Override
    public DefaultResponse findUserDetailBoard(BoardLayer boardLayer) throws LgException {
        BoardQ board = boardDao.findBoardDetail(boardLayer); // 게시판 상세 조회
        board.setCreated(boardRepository.countByBoardIdAndUserTb(board.getBoardId(), boardLayer.getUserTb())); // 작성자 플래그
        return new FindUBDResponse(board);
    }

}
