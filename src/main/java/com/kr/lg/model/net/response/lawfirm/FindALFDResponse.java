package com.kr.lg.model.net.response.lawfirm;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.LawFirmQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindALFDResponse extends DefaultResponse { // FindAnonymousLawFirmDetailResponse

    public LawFirmQ lawFirm;

}
