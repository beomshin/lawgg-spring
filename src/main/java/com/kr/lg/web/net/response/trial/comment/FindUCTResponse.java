package com.kr.lg.web.net.response.trial.comment;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.querydsl.TrialQ;
import com.vdurmont.emoji.EmojiParser;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindUCTResponse extends DefaultResponse { // FindUserCommentTriaResponse

    private List<TrialQ> comments;
    private Integer totalElements;
    private Long rootId;

    public FindUCTResponse(Long rootId, List<TrialQ> comments) {
        comments.stream().forEach(it -> it.setContent(EmojiParser.parseToUnicode(it.getContent())));
        this.rootId = rootId;
        this.comments = comments;
        this.totalElements = comments.size();
    }
}
