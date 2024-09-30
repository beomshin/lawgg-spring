package com.kr.lg.module.lawfirm.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "로펌 리스트 조회 요청 Body")
public class FindLawFirmsRequest {

    @Schema(description = "페이지")
    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @Schema(description = "페이지개수")
    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

    @Schema(description = "검색조건")
    private Integer subject;

    @Schema(description = "키워드")
    private String keyword;

    public FindLawFirmsRequest() {
        this.page = 0;
        this.pageNum = 16;
        this.subject = 1; // 현재 고정
    }
}
