package com.kr.lg.web.net.request.trial.base;

import com.kr.lg.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "트라이얼 유저 인증 로그인 요청 바디")
public class LoginUTRequest implements RootRequest { // LoginUserTrialRequest

    @ApiModelProperty(value = "트라이얼 아이디")
    @NotNull(message = "게시판아이디가 입력되어있지않습니다.")
    private Long id;

    @ApiModelProperty(value = "패스워드")
    @NotNull(message = "패스워드가 입력되어있지않습니다.")
    private String password;
}
