package com.kr.lg.module.board.model.mapper;

import com.kr.lg.model.mapper.MapperParam;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindBoardsParamData implements MapperParam {

    private Integer type; // 라인 타입
    private Integer subject; // 조회 타입
    private String keyword; // 키워드
    private Long userId; // 유저 아이디
    private Long lawFirmId; // 로펌 아이디

}
