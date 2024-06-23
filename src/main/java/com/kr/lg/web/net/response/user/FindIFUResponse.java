package com.kr.lg.web.net.response.user;

import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.querydsl.UserQ;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindIFUResponse extends DefaultResponse { // FindInfoUserResponse

    UserQ user;
}
