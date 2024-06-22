package com.kr.lg.model.web.net.response.trial.comment;

import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.querydsl.TrialQ;
import com.vdurmont.emoji.EmojiParser;
import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindACTResponse extends DefaultResponse { // FindAnonymousCommentTrialResponse

    private List<TrialQ> comments;
    private Integer totalElements;
    private Long rootId;

    public FindACTResponse(Long rootId, List<TrialQ> comments) {
        comments.stream().forEach(it -> it.setContent(EmojiParser.parseToUnicode(it.getContent())));
        this.rootId = rootId;
        this.comments = comments;
        this.totalElements = comments.size();
    }
}
