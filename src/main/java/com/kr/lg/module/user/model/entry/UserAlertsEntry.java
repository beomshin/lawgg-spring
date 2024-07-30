package com.kr.lg.module.user.model.entry;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class UserAlertsEntry {

    private long alertId; // 유저 알림 아이디

}
