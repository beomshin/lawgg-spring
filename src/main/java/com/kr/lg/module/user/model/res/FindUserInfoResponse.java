package com.kr.lg.module.user.model.res;

import com.kr.lg.module.user.model.entry.UserEntry;
import com.kr.lg.model.global.AbstractSpec;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "회원 정보 조회 응답 Body")
public class FindUserInfoResponse extends AbstractSpec {

    UserEntry user;
}
