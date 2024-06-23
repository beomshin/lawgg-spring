package com.kr.lg.web.net.response.auth;

import com.kr.lg.web.common.root.DefaultResponse;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PublicCertificationsDanalResponse extends DefaultResponse {

    private String data;
}
