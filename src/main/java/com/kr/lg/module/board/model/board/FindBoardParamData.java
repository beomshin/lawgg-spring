package com.kr.lg.module.board.model.board;

import com.kr.lg.web.dto.mapper.MapperParam;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindBoardParamData implements MapperParam {

    private long boardId; // 게시판 아이디
    private Long userId; // 유저 아이디
}
