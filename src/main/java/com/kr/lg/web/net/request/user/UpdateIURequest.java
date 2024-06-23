package com.kr.lg.web.net.request.user;

import com.kr.lg.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "유저 정보 변경하기 요청 바디")
public class UpdateIURequest implements RootRequest { // UpdateInfoUserRequest

    @ApiModelProperty(value = "닉네임")
    private String nickName;

    @ApiModelProperty(value = "패스워드")
    private String password;

    @ApiModelProperty(value = "이메일")
    private String email;
}
