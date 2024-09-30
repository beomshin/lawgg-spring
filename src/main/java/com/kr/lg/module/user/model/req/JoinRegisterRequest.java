package com.kr.lg.module.user.model.req;


import lombok.*;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
//@ApiModel(value = "회원가입 페이지 이동 Body")
public class JoinRegisterRequest {

//    @ApiModelProperty(value = "유저 아이디")
    private Boolean accept;

}
