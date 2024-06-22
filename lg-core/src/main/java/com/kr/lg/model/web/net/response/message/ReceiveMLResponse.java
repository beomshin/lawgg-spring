package com.kr.lg.model.web.net.response.message;

import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.querydsl.MessageQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveMLResponse extends DefaultResponse { // ReceiveMessageListResponse
    List<MessageQ> messages;
    private Long totalElements;
    private Integer totalPage;
    private Integer curPage;

    public ReceiveMLResponse(Page<MessageQ> list) {
        this.messages = list.getContent();
        this.totalElements = list.getTotalElements();
        this.totalPage = list.getTotalPages();
        this.curPage = list.getNumber();
    }
}
