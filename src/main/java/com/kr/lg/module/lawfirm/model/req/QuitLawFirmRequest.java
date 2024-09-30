package com.kr.lg.module.lawfirm.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "로펌 탈퇴 요청 Body")
public class QuitLawFirmRequest {

    @Schema(description = "로펌 아이디")
    @NotNull(message = "로펌 아이디가 입력되어있지않습니다.")
    private Long lawfirmId;
}
