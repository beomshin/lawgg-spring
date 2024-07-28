package com.kr.lg.module.user.model.res;

import com.kr.lg.web.dto.root.DefaultResponse;
import com.kr.lg.model.querydsl.UserQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindIFUResponse extends DefaultResponse { // FindInfoUserResponse

    UserQ user;
}
