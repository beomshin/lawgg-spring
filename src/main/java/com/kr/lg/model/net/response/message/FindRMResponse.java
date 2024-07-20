package com.kr.lg.model.net.response.message;

import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.querydsl.MessageQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindRMResponse extends DefaultResponse { // FindReceiveMessageResponse

    private MessageQ message;
}
