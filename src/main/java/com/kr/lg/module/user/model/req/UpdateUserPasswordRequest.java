package com.kr.lg.module.user.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원 비밀번호 업데이트 요청 Body")
public class UpdateUserPasswordRequest {

    @Schema(description = "로그인 아이디")
    @NotBlank(message = "loginId 값이 입력되어있지않습니다.")
    private String oldPassword;

    @Schema(description = "패스워드")
    @NotBlank(message = "패스워드 값이 입력되어있지않습니다.")
    private String newPassword;


}
