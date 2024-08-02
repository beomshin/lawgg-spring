package com.kr.lg.module.thirdparty.model.res;

import com.kr.lg.model.common.AbstractSpec;
import lombok.*;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SendEmailResponse extends AbstractSpec {

    private String txId;

}
