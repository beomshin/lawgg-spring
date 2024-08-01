package com.kr.lg.module.thirdparty.model.res;

import com.kr.lg.web.dto.root.AbstractSpec;
import lombok.*;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PublicCertificationsDanalResponse extends AbstractSpec {

    private String data;
}
