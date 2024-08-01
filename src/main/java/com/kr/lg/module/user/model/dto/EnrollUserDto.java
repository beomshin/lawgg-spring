package com.kr.lg.module.user.model.dto;

import com.kr.lg.common.enums.entity.type.SnsType;
import com.kr.lg.db.entities.TierTb;
import com.kr.lg.enums.AuthEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrollUserDto {

    private String loginId;
    private String password;
    private String nickName;
    private Integer personalPeriod;
    private SnsType snsType;
    private AuthEnum authFlag;
    private TierTb tierTb; // 티어

}
