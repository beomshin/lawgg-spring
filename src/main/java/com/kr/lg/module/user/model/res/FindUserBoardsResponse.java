package com.kr.lg.module.user.model.res;

import com.kr.lg.web.dto.root.AbstractSpec;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ApiModel(value = "로그인 유저 생성 포지션 게시판 응답 Body")
public class FindUserBoardsResponse extends AbstractSpec {

    private List<?> list;
    private long totalElements;
    private int totalPage;
    private int curPage;
}
