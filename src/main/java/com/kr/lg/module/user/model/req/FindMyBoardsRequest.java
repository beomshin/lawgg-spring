package com.kr.lg.module.user.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "유저 게시판 리스트 조회 Body")
public class FindMyBoardsRequest {

    @Schema(description = "페이지")
    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @Schema(description = "페이지개수")
    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

    @Schema(description = "토픽")
    @NotNull(message = "토픽이 입력되어있지않습니다.")
    private Integer topic;

    @Schema(description = "검색조건")
    private Integer subject;

    @Schema(description = "키워드")
    private String keyword;

    public FindMyBoardsRequest() {
        this.page = 0;
        this.pageNum = 10;
        this.topic = 1;
        this.subject = 1;
    }
}
