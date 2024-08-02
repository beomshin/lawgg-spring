package com.kr.lg.module.lawfirm.model.res;

import com.kr.lg.model.common.AbstractSpec;
import lombok.*;

import java.util.List;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindLawFirmsResponse extends AbstractSpec {

    private List<?> lawFirms;
    private Long totalElements;
    private Integer totalPage;

}
