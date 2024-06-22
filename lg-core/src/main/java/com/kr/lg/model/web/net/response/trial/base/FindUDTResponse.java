package com.kr.lg.model.web.net.response.trial.base;

import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.querydsl.TrialQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindUDTResponse extends DefaultResponse { // FindUserDetailTrialResponse

    TrialQ trial;
}
