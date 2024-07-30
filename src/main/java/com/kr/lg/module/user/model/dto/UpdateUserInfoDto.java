package com.kr.lg.module.user.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUserInfoDto {

    private long userId;
    private String nickName;
    private String password;
    private String email;
    private String hashEmail;

}
