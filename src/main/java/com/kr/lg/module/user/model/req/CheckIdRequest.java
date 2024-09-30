package com.kr.lg.module.user.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "아이디 중복 체크 요청 Body")
public class CheckIdRequest {

    @Schema(description = "로그인 아이디")
    @NotBlank(message = "로그인 아이디가 누락되었습니다.")
    private String loginId;


}
