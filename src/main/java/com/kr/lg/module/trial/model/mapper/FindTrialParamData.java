package com.kr.lg.module.trial.model.mapper;

import com.kr.lg.web.dto.mapper.MapperParam;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindTrialParamData implements MapperParam {

    private Long trialId;
    private Integer subject;
    private String keyword;
    private Long lawFirmId;

}
