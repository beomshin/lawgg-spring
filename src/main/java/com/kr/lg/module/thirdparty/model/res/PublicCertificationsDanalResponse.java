package com.kr.lg.module.thirdparty.model.res;

import com.kr.lg.web.dto.root.DefaultResponse;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PublicCertificationsDanalResponse extends DefaultResponse {

    private String data;
}
