package com.kr.lg.module.user.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "회원 알림 리스트 조회 요청 Body")
public class FindMyAlertRequest {

    @Schema(description = "페이지")
    @NotNull(message = "페이지가 입력되어있지않습니다.")
    private Integer page;

    @Schema(description = "페이지개수")
    @NotNull(message = "페이지개수가 입력되어있지않습니다.")
    private Integer pageNum;

    @Schema(description = "키워드")
    private String keyword;

    public FindMyAlertRequest() {
        this.page = 0;
        this.pageNum = 10;
    }

}
