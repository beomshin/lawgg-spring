package com.kr.lg.model.net.response.message;

import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.MessageQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindSMResponse extends DefaultResponse { // FindSendMessageResponse

    private MessageQ message;
}
