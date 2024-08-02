package com.kr.lg.module.trial.model.res;

import com.kr.lg.model.common.AbstractSpec;
import lombok.*;

import java.util.List;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindTrialsResponse extends AbstractSpec {

    private List<?> list;
    private Long totalElements;
    private Integer totalPage;
    private Integer curPage;

}
