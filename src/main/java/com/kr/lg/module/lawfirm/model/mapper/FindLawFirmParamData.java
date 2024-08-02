package com.kr.lg.module.lawfirm.model.mapper;

import com.kr.lg.model.mapper.MapperParam;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindLawFirmParamData implements MapperParam {

    private String keyword;
    private Integer subject;
    private long lawFirmId;

}
