
package com.kr.lg.module.message.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "메세지 발신 요청 Body")
public class SendMessageRequest {

    @Schema(description = "받는이 아이디")
    @NotBlank(message = "받는이가 입력되어있지않습니다.")
    private String receiver;

    @Schema(description = "제목")
    @NotBlank(message = "제목이 입력되어있지않습니다.")
    private String title;

    @Schema(description = "내용")
    @NotBlank(message = "내용이 입력되어있지않습니다.")
    private String content;

}
