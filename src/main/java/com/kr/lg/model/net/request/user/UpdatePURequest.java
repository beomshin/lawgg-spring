package com.kr.lg.model.net.request.user;

import com.kr.lg.model.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "유저 비밀번호 업데이트 요청 바디")
public class UpdatePURequest implements RootRequest { // UpdatePwUserRequest

    @ApiModelProperty(value = "로그인 아이디", required = true)
    @NotBlank(message = "loginId 값이 입력되어있지않습니다.")
    private String loginId;

    @ApiModelProperty(value = "패스워드", required = true)
    @NotBlank(message = "패스워드 값이 입력되어있지않습니다.")
    private String password;


}
