package com.kr.lg.module.user.model.req;


import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
//@ApiModel(value = "유저 게시판 리스트 조회 Body")
public class FindMyBoardsRequest {

//    @ApiModelProperty(value = "페이지", required = true)
    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

//    @ApiModelProperty(value = "페이지개수", required = true)
    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

//    @ApiModelProperty(value = "토픽", required = true)
    @NotNull(message = "토픽이 입력되어있지않습니다.")
    private Integer topic;

//    @ApiModelProperty(value = "검색조건")
    private Integer subject;

//    @ApiModelProperty(value = "키워드")
    private String keyword;

    public FindMyBoardsRequest() {
        this.page = 0;
        this.pageNum = 10;
        this.topic = 1;
        this.subject = 1;
    }
}
