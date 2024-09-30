package com.kr.lg.module.thirdparty.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "이메일 인증 번호 발신 요청 Body")
public class SendEmailRequest {

    @Schema(description = "이메일")
    @NotBlank(message = "이메일이 입력되어있지않습니다.")
    private String email;
}
