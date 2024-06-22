package com.kr.lg.model.web.net.request.sign;

import com.kr.lg.model.web.common.root.RootRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "이메일 인증 번호 발신 요청 바디")
public class SendERequest implements RootRequest { // SendEmailRequest

    @ApiModelProperty(value = "이메일", required = true)
    @NotBlank(message = "이메일이 입력되어있지않습니다.")
    private String email;
}
