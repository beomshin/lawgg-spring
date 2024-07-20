package com.kr.lg.model.net.response.trial.base;

import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.querydsl.TrialQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindUDTResponse extends DefaultResponse { // FindUserDetailTrialResponse

    TrialQ trial;
}
