package com.kr.lg.module.user.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "회원 비밀번호 업데이트 요청 Body")
public class UpdateUserPasswordRequest {

    @ApiModelProperty(value = "로그인 아이디", required = true)
    @NotBlank(message = "loginId 값이 입력되어있지않습니다.")
    private String oldPassword;

    @ApiModelProperty(value = "패스워드", required = true)
    @NotBlank(message = "패스워드 값이 입력되어있지않습니다.")
    private String newPassword;


}
