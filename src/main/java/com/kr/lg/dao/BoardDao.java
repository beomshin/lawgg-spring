package com.kr.lg.dao;

import com.kr.lg.web.querydsl.BoardQ;
import com.kr.lg.web.common.layer.BoardLayer;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.entities.BoardTb;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

public interface BoardDao {

    Page<BoardQ> findAllBoardList(BoardLayer requestDto, Pageable pageable);
    Page<BoardQ> findUserBoardList(BoardLayer requestDto, Pageable pageable);
    Page<BoardQ> findLawFirmBoardList(BoardLayer requestDto, Pageable pageable);
    BoardQ findBoardDetail(BoardLayer requestDto) throws LgException;
    @Transactional
    BoardTb saveBoard(BoardLayer requestDto);
    @Transactional
    int updateBoard(BoardLayer requestDto);
}