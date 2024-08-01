package com.kr.lg.module.user.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "회원 등록 요청 Body")
public class EnrollUserRequest {

    @ApiModelProperty(value = "유저 아이디", required = true)
    @NotBlank(message = "로그인 아이디가 잘못되었습니다.")
    private String loginId;

    @ApiModelProperty(value = "패스워드", required = true)
    @NotBlank(message = "로그인 비밀번호가 잘못되었습니다.")
    private String password;

    @ApiModelProperty(value = "닉네임")
    private String nickName;

    @ApiModelProperty(value = "이름")
    private String name;

    @ApiModelProperty(value = "개인정보 유효기간", required = true)
    @NotNull(message = "개인정보 유효기간이 입력되어있지않습니다.")
    private Integer personalPeriod;


}
