package com.kr.lg.model.web.net.response.board.comment;

import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.querydsl.BoardQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindAPCBResponse extends DefaultResponse { // FindAnonymousParentCommentBoardResponse

    private List<BoardQ> comments;
    private Long totalElements;

    public FindAPCBResponse(Page<BoardQ> comments) {
        this.comments = comments.getContent();
        this.totalElements = comments.getTotalElements();
    }
}
