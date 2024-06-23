package com.kr.lg.web.net.response.trial.comment;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.querydsl.TrialQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindACCTResponse extends DefaultResponse { // FindAnonymousChildrenCommentTrialResponse

    private List<TrialQ> comments;
    private Long totalElements;

    public FindACCTResponse(Page<TrialQ> comments) {
        this.comments = comments.getContent();
        this.totalElements = comments.getTotalElements();
    }
}
