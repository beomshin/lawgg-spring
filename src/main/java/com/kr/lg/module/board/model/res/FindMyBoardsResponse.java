package com.kr.lg.module.board.model.res;

import com.kr.lg.web.dto.root.AbstractSpec;
import lombok.*;

import java.util.List;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindMyBoardsResponse extends AbstractSpec { // FindUserBoardListResponse

    private List<?> list;
    private Long totalElements;
    private Integer totalPage;
    private Integer curPage;

}