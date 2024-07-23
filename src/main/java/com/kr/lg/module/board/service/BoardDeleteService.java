package com.kr.lg.module.board.service;

import com.kr.lg.module.board.exception.BoardException;

public interface BoardDeleteService {
    void deleteBoard(long boardId) throws BoardException;

}
