package com.kr.lg.model.web.net.response.user;

import com.kr.lg.model.web.common.root.DefaultResponse;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUPResponse extends DefaultResponse { // UpdateUserProfileResponse

    private String profile;
}
