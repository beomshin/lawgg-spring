package com.kr.lg.model.web.net.response.message;

import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.querydsl.MessageQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindRMResponse extends DefaultResponse { // FindReceiveMessageResponse

    private MessageQ message;
}
