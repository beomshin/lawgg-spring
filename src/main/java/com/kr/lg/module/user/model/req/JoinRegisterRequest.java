package com.kr.lg.module.user.model.req;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "회원가입 페이지 이동 Body")
public class JoinRegisterRequest {

    @Schema(description = "유저 아이디")
    private Boolean accept;

}
