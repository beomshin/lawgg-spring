package com.kr.lg.module.user.model.mapper;

import com.kr.lg.model.mapper.MapperParam;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindUserParamData implements MapperParam {

    private long userId;
    private String keyword;

}
