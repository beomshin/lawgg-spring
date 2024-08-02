package com.kr.lg.module.lawfirm.model.res;

import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.model.common.AbstractSpec;
import lombok.*;

@Getter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class FindLawFirmWithLoginResponse extends AbstractSpec {

    public LawFirmEntry lawFirm;
}
