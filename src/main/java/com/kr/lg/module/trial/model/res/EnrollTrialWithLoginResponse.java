package com.kr.lg.module.trial.model.res;

import com.kr.lg.model.global.AbstractSpec;
import lombok.*;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class EnrollTrialWithLoginResponse extends AbstractSpec {

    private Long id;
}
