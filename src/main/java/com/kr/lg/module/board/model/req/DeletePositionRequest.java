package com.kr.lg.module.board.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "비로그인 포지션 게시판 삭제 요청 Body")
public class DeletePositionRequest {

    @Schema(description = "게시판 아이디")
    @NotNull(message = "게시판 아이디가 누락 되었습니다.")
    private Long boardId;

    @Schema(description = "게시판 비밀번호")
    private String password;

}
