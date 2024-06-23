package com.kr.lg.service.board.base;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.layer.BoardLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface BoardFindService {


    DefaultResponse findAllListBoard(BoardLayer boardLayer) throws LgException;
    DefaultResponse findUserListBoard(BoardLayer boardLayer) throws LgException;
    DefaultResponse findLawFirmListBoard(BoardLayer boardLayer) throws LgException;
    DefaultResponse findAnonymousDetailBoard(BoardLayer boardLayer) throws LgException;
    DefaultResponse findUserDetailBoard(BoardLayer boardLayer) throws LgException;

}
