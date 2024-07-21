package com.kr.lg.module.mapper;

import com.kr.lg.LgWasApplication;
import com.kr.lg.module.board.mapper.BoardFindMapper;
import com.kr.lg.module.board.model.board.FindBoardParamData;
import com.kr.lg.module.board.model.dto.BoardEntry;
import com.kr.lg.module.board.sort.BoardSort;
import com.kr.lg.web.dto.mapper.MapperParam;
import com.kr.lg.web.dto.mapper.BoardParam;
import com.kr.lg.module.board.model.board.FindBoardsParamData;
import com.kr.lg.module.config.MockMvcConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
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
    public void test() {
        PageRequest pageRequest  = PageRequest.of(1, 10 , BoardSort.notificationSortWithDesc().and(BoardSort.dateTimeWithDesc()));
        BoardParam<MapperParam> param = new BoardParam<>( FindBoardsParamData.builder()
                .type(5)
                .subject(0)
                .keyword(null)
                .build(), pageRequest);

        List<BoardEntry> boards = boardFindMapper.findBoards(param);

        log.info("{}", boards);
        log.info("{}", boards.size());
    }

    @Test
    @Transactional
    @DisplayName("매퍼 테스트2")
    public void test2() {

        FindBoardParamData param = FindBoardParamData.builder().boardId(5273L).userId(119L).build();

        BoardEntry board= boardFindMapper.findBoard(param);

        log.info("{}", board);
    }
}
