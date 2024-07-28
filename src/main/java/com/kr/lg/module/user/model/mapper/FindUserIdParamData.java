package com.kr.lg.module.user.model.mapper;

import com.kr.lg.web.dto.mapper.MapperParam;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindUserIdParamData implements MapperParam {

    private String ci;

}
