package com.kr.lg.module.user.model.res;

import com.kr.lg.model.common.AbstractSpec;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "프로필 업데이트 조회 응답 Body")
public class UpdateUPResponse extends AbstractSpec {

    private String profile;
}
