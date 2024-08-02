package com.kr.lg.module.user.model.res;

import com.kr.lg.model.common.AbstractSpec;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "회원 아이디 조회 응답 Body")
public class FindUserIdResponse extends AbstractSpec {

    private List<?> ids;
}
