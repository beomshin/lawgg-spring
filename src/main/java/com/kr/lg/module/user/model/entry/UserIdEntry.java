package com.kr.lg.module.user.model.entry;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;


@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // NULL 제외 속성
public class UserIdEntry {

    private long userId; // 유저 식별자
    private String profile; // 프로필
    private String nickName; // 닉네임
    private Integer delFlag; // 삭제 플래그
    private Integer status; // 상태
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일
    private Integer snsType; // sns 타입


}
