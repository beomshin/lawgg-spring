package com.kr.lg.module.thirdparty.model.res;

import com.kr.lg.model.common.AbstractSpec;
import lombok.*;

import java.util.List;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class EnrollFileResponse extends AbstractSpec {

    private List<?> files;

}
