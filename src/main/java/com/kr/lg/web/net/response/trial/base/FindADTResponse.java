package com.kr.lg.web.net.response.trial.base;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.querydsl.TrialQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindADTResponse extends DefaultResponse { // FindAnonymousDetailTrialResponse

    TrialQ trial;
}
