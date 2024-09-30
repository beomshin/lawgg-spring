package com.kr.lg.module.user.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원 등록 요청 Body")
public class EnrollUserRequest {

    @Schema(description = "유저 아이디")
    @NotBlank(message = "로그인 아이디가 잘못되었습니다.")
    private String loginId;

    @Schema(description = "패스워드")
    @NotBlank(message = "로그인 비밀번호가 잘못되었습니다.")
    private String password;

    @Schema(description = "닉네임")
    private String nickName;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "개인정보 유효기간")
    @NotNull(message = "개인정보 유효기간이 입력되어있지않습니다.")
    private Integer personalPeriod;


}
