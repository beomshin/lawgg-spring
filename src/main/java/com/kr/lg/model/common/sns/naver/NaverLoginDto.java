package com.kr.lg.model.common.sns.naver;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NaverLoginDto {

    private String id;
    private String nickname;
    private String profileImage;
    private String email;
    private String name;

}
