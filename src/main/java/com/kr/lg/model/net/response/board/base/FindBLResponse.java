package com.kr.lg.model.net.response.board.base;

import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.querydsl.BoardQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindBLResponse extends DefaultResponse { // FindBoardListResponse

    private List list;
    private Long totalElements;
    private Integer totalPage;
    private Integer curPage;


    public FindBLResponse(Page<BoardQ> boards) {
        this.list = boards.getContent();
        this.totalElements = boards.getTotalElements();
        this.totalPage = boards.getTotalPages();
        this.curPage = boards.getNumber();
    }
}
