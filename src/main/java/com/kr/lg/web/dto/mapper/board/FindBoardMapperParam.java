package com.kr.lg.web.dto.mapper.board;

import com.kr.lg.web.dto.mapper.MapperParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindBoardMapperParam implements MapperParam {

    private Integer type; // 라인 타입
    private Integer subject; // 조회 타입
    private String keyword; // 키워드

}
