package com.kr.lg.module.board.service.impl;

import com.kr.lg.enums.BoardTopicEnum;
import com.kr.lg.module.board.mapper.BoardFindMapper;
import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.model.req.FindBoardRequest;
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

    private final String SORT_NOTIFICATION_POST_TYPE = "CASE WHEN postType = 99 THEN 2 WHEN postType = 98 THEN 1 ELSE 0 END";
    private final String SORT_DATE_TIME = "DATE_FORMAT(bt.writeDt, '%Y-%m-%d %H:%i:%s')";
    private final String SORT_DATE = "DATE_FORMAT(bt.writeDt, '%Y-%m-%d')";
    private final String SORT_BEST_POST_TYPE = "CASE WHEN postType = 3 THEN 2 WHEN postType = 2 THEN 1 ELSE 0 END";

    @Override
    public Page<BoardEntry> findBoards(FindBoardRequest request) {
        Sort sort = null;
        if (BoardTopicEnum.NEW_TOPIC == BoardTopicEnum.of(request.getTopic())) {
            sort = Sort.by(SORT_NOTIFICATION_POST_TYPE).descending() // 공지순
                    .and(Sort.by(SORT_DATE_TIME).descending()); // 날짜&시간 순
        } else if (BoardTopicEnum.HOT_TOPIC == BoardTopicEnum.of(request.getTopic())) {
            sort = Sort.by(SORT_NOTIFICATION_POST_TYPE).descending() // 공지 순
                    .and(Sort.by(SORT_DATE).descending()) // 날짜순
                    .and(Sort.by(SORT_BEST_POST_TYPE).descending()); // 인기순
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), sort); // pageable 생성
        MapperParam param = new FindBoardMapperParam(request.getType(), request.getType(), request.getKeyword()); // mapper param 생성
        List<BoardEntry> content = boardFindMapper.findBoards(new BoardParam<>(param, pageable)); // board 조회
        long count = boardFindMapper.findBoardsCnt(param); // board 개수 조회
        return new PageImpl<>(content, pageable, count); // pageable 생성
    }
}
