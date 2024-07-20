package com.kr.lg.module.mapper;

import com.kr.lg.LgWasApplication;
import com.kr.lg.module.board.mapper.BoardFindMapper;
import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.board.BoardParam;
import com.kr.lg.web.dto.mapper.board.FindBoardMapperParam;
import com.kr.lg.module.config.MockMvcConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest(
        classes = LgWasApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Import(MockMvcConfig.class) // Test config import 설정 (커스텀 mockMvc)
public class MapperTest {

    private final BoardFindMapper boardFindMapper;

    public MapperTest(@Autowired BoardFindMapper boardFindMapper) {
        this.boardFindMapper = boardFindMapper;
    }

    @Test
    @Transactional
    @DisplayName("매퍼 테스트")
    public void test() throws Exception {
        PageRequest pageRequest  = PageRequest.of(1, 10 , Sort.by("CASE WHEN postType = 99 THEN 2 WHEN postType = 98 THEN 1 ELSE 0 END").descending().and(Sort.by("DATE_FORMAT(bt.writeDt, '%Y-%m-%d %H:%i:%s')").descending()));
        BoardParam<MapperParam> param = new BoardParam<>(new FindBoardMapperParam(5, null, null), pageRequest);

        List<BoardEntry> boards = boardFindMapper.findBoards(param);

        System.out.println(boards);
        System.out.println(boards.size());
    }
}
