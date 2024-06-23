package com.kr.lg.model.net.response.trial.base;

import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.TrialQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindADTResponse extends DefaultResponse { // FindAnonymousDetailTrialResponse

    TrialQ trial;
}
