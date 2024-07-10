package com.kr.lg.model.net.response.board.comment;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.BoardQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindACCBResponse extends DefaultResponse { // FindAnonymousChildrenCommentBoardResponse

    private List<BoardQ> comments;
    private Long totalElements;

    public FindACCBResponse(Page<BoardQ> comments) {
        this.comments = comments.getContent();
        this.totalElements = comments.getTotalElements();
    }
}
