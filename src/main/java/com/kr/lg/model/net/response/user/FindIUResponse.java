package com.kr.lg.model.net.response.user;

import com.kr.lg.model.common.root.DefaultResponse;
import com.kr.lg.model.querydsl.UserQ;
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
