package com.kr.lg.model.net.response.trial.base;

import com.kr.lg.model.common.root.DefaultResponse;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class EnrollUTResponse extends DefaultResponse { // EnrollUserTrialResponse

    private Long id;
}
