package com.kr.lg.model.net.response.user;

import com.kr.lg.web.dto.root.DefaultResponse;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUPResponse extends DefaultResponse { // UpdateUserProfileResponse

    private String profile;
}
