package com.kr.lg.model.net.response.board.comment;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.BoardQ;
import com.vdurmont.emoji.EmojiParser;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindUBCResponse extends DefaultResponse { // FindUserBoardCommentResponse

    private List<BoardQ> comments;
    private Integer totalElements;
    private Long rootId;

    public FindUBCResponse(Long rootId, List<BoardQ> comments) {
        comments.stream().forEach(it -> it.setContent(EmojiParser.parseToUnicode(it.getContent())));
        this.rootId = rootId;
        this.comments = comments;
        this.totalElements = comments.size();
    }
}
