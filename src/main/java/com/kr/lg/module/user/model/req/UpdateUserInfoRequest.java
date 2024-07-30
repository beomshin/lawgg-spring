package com.kr.lg.module.user.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "회원 정보 업데이트 요청 Body")
public class UpdateUserInfoRequest {

    @ApiModelProperty(value = "닉네임")
    private String nickName;

    @ApiModelProperty(value = "패스워드")
    private String password;

    @ApiModelProperty(value = "이메일")
    private String email;
}
