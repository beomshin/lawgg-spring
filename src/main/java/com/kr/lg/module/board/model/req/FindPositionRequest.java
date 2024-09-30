package com.kr.lg.module.board.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "포지션 게시판 조회 Body")
public class FindPositionRequest {

    @Schema(description = "페이지")
    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @Schema(description = "라인타입")
    @NotNull(message = "라인타입이 입력되어있지않습니다.")
    private Integer type;

    @Schema(description = "페이지개수")
    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

    @Schema(description = "검색순서")
    @NotNull(message = "토픽이 입력되어있지않습니다.")
    private Integer topic;

    @Schema(description = "검색조건")
    private Integer subject;

    @Schema(description = "키워드")
    private String keyword;

    public FindPositionRequest() {
        this.page = 0;
        this.type = 0;
        this.pageNum = 30;
        this.topic = 1;
        this.subject = 1; // 현재 고정
    }
}
