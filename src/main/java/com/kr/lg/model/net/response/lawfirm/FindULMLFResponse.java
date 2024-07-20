package com.kr.lg.model.net.response.lawfirm;

import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.querydsl.LawFirmQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindULMLFResponse extends DefaultResponse { // FindUserListMyLawFirmResponse

    private List list;
    private Long totalElements;
    private Integer totalPage;

    public FindULMLFResponse(Page<LawFirmQ> list) {
        this.list = list.getContent();
        this.totalElements = list.getTotalElements();
        this.totalPage = list.getTotalPages();
    }
}
