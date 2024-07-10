package com.kr.lg.model.net.response.sign;

import com.kr.lg.web.common.root.DefaultResponse;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class SignSEResponse extends DefaultResponse { // SignSendEmailResponse

    private String txId;

}
