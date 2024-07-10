package com.kr.lg.model.net.response.lawfirm;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.LawFirmQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindLFDResponse extends DefaultResponse { // findLawFirmDetailResponse

    public LawFirmQ lawFirm;

}
