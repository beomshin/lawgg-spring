package com.kr.lg.model.net.response.lawfirm;

import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.LawFirmQ;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindALFLResponse extends DefaultResponse { // FindAllLawFirmListResponse

    private List lawFirms;
    private Long totalElements;
    private Integer totalPage;

    public FindALFLResponse(Page<LawFirmQ> list) {
        this.lawFirms = list.getContent();
        this.totalElements = list.getTotalElements();
        this.totalPage = list.getTotalPages();
    }
}
