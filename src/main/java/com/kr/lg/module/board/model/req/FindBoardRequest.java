package com.kr.lg.module.board.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ApiModel(value = "포지션 게시판 조회 Body")
public class FindBoardRequest {

    @ApiModelProperty(value = "페이지", required = true)
    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @ApiModelProperty(value = "라인타입", required = true)
    @NotNull(message = "라인타입이 입력되어있지않습니다.")
    private Integer type;

    @ApiModelProperty(value = "페이지개수", required = true)
    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

    @ApiModelProperty(value = "검색순서", required = true)
    @NotNull(message = "토픽이 입력되어있지않습니다.")
    private Integer topic;

    @ApiModelProperty(value = "검색조건")
    private Integer subject;

    @ApiModelProperty(value = "키워드")
    private String keyword;

    public FindBoardRequest() {
        this.page = 0;
        this.type = 0;
        this.pageNum = 30;
        this.topic = 1;
        this.subject = 1;
    }
}
