package com.kr.lg.module.board.model.res;

import com.kr.lg.web.dto.root.AbstractSpec;
import lombok.*;

import java.util.List;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindBoardsResponse extends AbstractSpec {

    private List<?> list; // 게시판 리스트

    private Long totalElements; // 게시판 총개수

    private Integer totalPage; // 게시판 페이지 개수

    private Integer curPage; // 현재 페이지 번호

}
