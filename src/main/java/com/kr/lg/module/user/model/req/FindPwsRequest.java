package com.kr.lg.module.user.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Schema(description = "회원 아이디 조회 요청 Body")
public class FindPwsRequest {

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "txId")
    private String txId;

    @Schema(description = "인증번호")
    private String code;

    @Schema(description = "로그인 아이디")
    private String loginId;
}
