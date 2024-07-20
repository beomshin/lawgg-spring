package com.kr.lg.model.net.response.trial.base;

import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.querydsl.TrialQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindALTResponse extends DefaultResponse { // FindAllListTrialResponse

    private List list;
    private Long totalElements;
    private Integer totalPage;
    private Integer curPage;

    public FindALTResponse(Page<TrialQ> list) {
        this.list = list.getContent();
        this.totalElements = list.getTotalElements();
        this.totalPage = list.getTotalPages();
        this.curPage = list.getNumber();
    }

}
