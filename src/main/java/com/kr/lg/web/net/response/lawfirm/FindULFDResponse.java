package com.kr.lg.web.net.response.lawfirm;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.querydsl.LawFirmQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindULFDResponse extends DefaultResponse { // FindUserLawFirmDetailResponse

    public LawFirmQ lawFirm;
}
