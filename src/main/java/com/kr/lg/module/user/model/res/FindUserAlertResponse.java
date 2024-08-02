package com.kr.lg.module.user.model.res;

import com.kr.lg.model.global.AbstractSpec;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "로그인 유저 알림 리스트 응답 Body")
public class FindUserAlertResponse extends AbstractSpec {

    private List<?> list;
    private Long totalElements;
    private Integer totalPage;
    private Integer curPage;

}
