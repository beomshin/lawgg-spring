package com.kr.lg.web.net.response.board.comment;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.querydsl.BoardQ;
import com.vdurmont.emoji.EmojiParser;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindABCResponse extends DefaultResponse { // FindAnonymousBoardCommentResponse

    private List<BoardQ> comments;
    private Integer totalElements;
    private Long rootId;

    public FindABCResponse(Long rootId, List<BoardQ> comments) {
        comments.stream().forEach(it -> it.setContent(EmojiParser.parseToUnicode(it.getContent())));
        this.rootId = rootId;
        this.comments = comments;
        this.totalElements = comments.size();
    }
}
