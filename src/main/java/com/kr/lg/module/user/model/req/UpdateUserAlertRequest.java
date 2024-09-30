package com.kr.lg.module.user.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원 알림 읽기 업데이트 요청 Body")
public class UpdateUserAlertRequest {

    @Schema(description = "알림 아이디")
    @NotNull(message = "알림 아이디가 없습니다.")
    private Long id;
}
