package com.kr.lg.module.thirdparty.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "이메일 인증 번호 발신 요청 Body")
public class SendEmailRequest {

    @ApiModelProperty(value = "이메일", required = true)
    @NotBlank(message = "이메일이 입력되어있지않습니다.")
    private String email;
}
