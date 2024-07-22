package com.kr.lg.module.board.service;

import com.kr.lg.module.board.exception.BoardException;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardDeleteService {

    void deleteBoard(long boardId) throws BoardException;

}
