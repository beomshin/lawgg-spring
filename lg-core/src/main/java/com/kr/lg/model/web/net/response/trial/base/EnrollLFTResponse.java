package com.kr.lg.model.web.net.response.trial.base;

import com.kr.lg.model.web.common.root.DefaultResponse;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class EnrollLFTResponse extends DefaultResponse { // EnrollLawFirmTrialResponse

    private Long id;
}
