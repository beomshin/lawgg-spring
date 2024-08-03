package com.kr.lg.module.user.model.dto;

import com.kr.lg.common.enums.entity.type.SnsType;
import com.kr.lg.db.entities.TierTb;
import com.kr.lg.common.enums.entity.flag.AuthFlag;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrollUserDto {

    private String loginId;
    private String snsId;
    private String password;
    private String nickName;
    private String profile;
    private String email;
    private String name;
    private Integer personalPeriod;
    private SnsType snsType;
    private AuthFlag authFlag;
    private TierTb tierTb; // 티어

}
