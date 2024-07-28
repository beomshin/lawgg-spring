package com.kr.lg.module.message.model.mapper;

import com.kr.lg.web.dto.mapper.MapperParam;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindMessageParamData implements MapperParam {

    private long receiverId; // 받는이 아이디
    private Integer subject; // 조회 타입
    private String keyword; // 키워드

}
