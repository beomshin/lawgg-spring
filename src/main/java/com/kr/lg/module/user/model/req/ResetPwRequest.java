package com.kr.lg.module.user.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ApiModel(value = "회원 아이디 조회 요청 Body")
public class ResetPwRequest {

    @ApiModelProperty(value = "txId", required = true)
    private String txId;

    @ApiModelProperty(value = "인증번호", required = true)
    private String code;

    @ApiModelProperty(value = "패스워드", required = true)
    private String password;

    private Long userId;
}
