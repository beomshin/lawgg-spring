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
public class BoardService2 implements com.kr.lg.module.board.service.BoardService2 {

    private final BoardFindMapper boardFindMapper;

    @Override
    public Page<BoardEntry> findBoards(FindBoardRequest request) {
        Sort sort = null;
        if (BoardTopicEnum.NEW_TOPIC == BoardTopicEnum.of(request.getTopic())) {
            sort = Sort.by("CASE WHEN postType = 99 THEN 2 WHEN postType = 98 THEN 1 ELSE 0 END").descending()
                    .and(Sort.by("DATE_FORMAT(bt.writeDt, '%Y-%m-%d %H:%i:%s')").descending());
        } else if (BoardTopicEnum.HOT_TOPIC == BoardTopicEnum.of(request.getTopic())) {
            sort = Sort.by("CASE WHEN postType = 99 THEN 2 WHEN postType = 98 THEN 1 ELSE 0 END").descending()
                    .and(Sort.by("DATE_FORMAT(bt.writeDt, '%Y-%m-%d %H:%i:%s')").descending())
                    .and(Sort.by("CASE WHEN postType = 3 THEN 2 WHEN postType = 2 THEN 1 ELSE 0 END").descending());
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), sort);
        MapperParam param = new FindBoardMapperParam(request.getType(), request.getType(), request.getKeyword());
        List<BoardEntry> content = boardFindMapper.findBoards(new BoardParam<>(param, pageable));
        long count = boardFindMapper.findBoardsCnt(param);
        return new PageImpl<>(content, pageable, count);
    }
}
