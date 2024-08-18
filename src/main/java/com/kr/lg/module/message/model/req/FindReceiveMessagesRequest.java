package com.kr.lg.module.message.model.req;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ApiModel(value = "수신 메세지 리스트 조회 요청 Body")
public class FindReceiveMessagesRequest {

    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

    private Integer subject;

    private String keyword;

    public FindReceiveMessagesRequest() {
        this.page = 0;
        this.pageNum = 10;
        this.subject = 1;
    }
}
