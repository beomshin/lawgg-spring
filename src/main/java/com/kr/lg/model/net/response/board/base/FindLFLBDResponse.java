package com.kr.lg.model.net.response.board.base;

import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.BoardQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindLFLBDResponse extends DefaultResponse { // FindLawFirmListBoardResponse

    private List<BoardQ> list;
    private Long totalElements;
    private Integer totalPage;
    private Integer curPage;

    public FindLFLBDResponse(Page<BoardQ> pages) {
        this.list = pages.getContent();
        this.totalElements = pages.getTotalElements();
        this.totalPage = pages.getTotalPages();
        this.curPage = pages.getNumber();
    }
}
