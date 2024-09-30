package com.kr.lg.module.board.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "포지션 게시판 추천 요청 Body")
public class RecommendPositionRequest {

    @Schema(description = "게시판 아이디")
    @NotNull(message = "게시판 아이디가 입력되어있지않습니다.")
    private Long boardId;
}
