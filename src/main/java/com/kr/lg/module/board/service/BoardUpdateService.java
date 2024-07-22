package com.kr.lg.module.board.service;

import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.model.dto.BoardUpdateDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardUpdateService {

    void updateBoard(BoardUpdateDto boardUpdateDto) throws BoardException;

}
