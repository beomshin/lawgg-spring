package com.kr.lg.module.board.service.impl;

import com.kr.lg.module.board.exception.BoardException;
import com.kr.lg.module.board.exception.BoardResultCode;
import com.kr.lg.module.board.mapper.BoardFindMapper;
import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.service.BoardFindService;
import com.kr.lg.module.board.mapper.board.BoardParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardFindServiceImpl implements BoardFindService {

    private final BoardFindMapper boardFindMapper;

    @Override
    public Page<BoardEntry> findBoards(BoardParam<?> param) throws BoardException {
        try {
            List<BoardEntry> content = boardFindMapper.findBoards(param); // board 조회
            long count = boardFindMapper.findBoardsCnt(param.getData()); // board 개수 조회
            return new PageImpl<>(content, param.getPageable(), count); // pageable 생성
        } catch (RuntimeException e) {
            log.error("", e);
            throw new BoardException(BoardResultCode.FAIL_FIND_BOARD);
        }
    }
}
