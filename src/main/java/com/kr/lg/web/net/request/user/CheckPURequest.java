package com.kr.lg.web.net.request.user;

import com.kr.lg.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "유저 비밀번호 인증 요청 바디")
public class CheckPURequest implements RootRequest { // CheckPwUserRequest

    @ApiModelProperty(value = "패스워드", required = true)
    @NotBlank(message = "password 값이 입력되어있지않습니다.")
    private String password;
}
