package com.kr.lg.web.net.response.user;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.querydsl.UserQ;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindIUResponse extends DefaultResponse { // FindIdUserResponse

    private List<UserQ> ids;
}
