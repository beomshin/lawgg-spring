package com.kr.lg.module.user.model.res;

import com.kr.lg.web.dto.root.DefaultResponse;
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
