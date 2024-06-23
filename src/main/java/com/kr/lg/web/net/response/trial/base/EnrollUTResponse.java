package com.kr.lg.web.net.response.trial.base;

import com.kr.lg.web.common.root.DefaultResponse;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class EnrollUTResponse extends DefaultResponse { // EnrollUserTrialResponse

    private Long id;
}
