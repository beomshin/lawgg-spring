package com.kr.lg.module.board.service.impl;

import com.kr.lg.enums.BoardTopicEnum;
import com.kr.lg.module.board.mapper.BoardFindMapper;
import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
import com.kr.lg.module.board.sort.BoardSort;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.board.BoardParam;
import com.kr.lg.web.dto.mapper.board.FindBoardMapperParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService implements com.kr.lg.module.board.service.BoardService {

    private final BoardFindMapper boardFindMapper;

    @Override
    public Page<BoardEntry> findBoards(FindBoardRequest request) {
        Sort sort = null;
        if (BoardTopicEnum.HOT_TOPIC == BoardTopicEnum.of(request.getTopic())) {
            sort = BoardSort.notificationSortWithDesc().and(BoardSort.dateWithDesc()).and(BoardSort.hotDesc());
        } else {
            sort = BoardSort.notificationSortWithDesc().and(BoardSort.dateTimeWithDesc());
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), sort); // pageable 생성
        MapperParam param = new FindBoardMapperParam(request.getType(), request.getType(), request.getKeyword()); // mapper param 생성
        List<BoardEntry> content = boardFindMapper.findBoards(new BoardParam<>(param, pageable)); // board 조회
        long count = boardFindMapper.findBoardsCnt(param); // board 개수 조회
        return new PageImpl<>(content, pageable, count); // pageable 생성
    }
}
