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
public class UserEntry {

    private long userId; // 유저 식별자
    private String loginId; // 로그인 아이디
    private String profile; // 프로필
    private String nickName; // 닉네임
    private String name; // 이름
    private String email; // 이메일
    private Integer boardCount; // 게시판 개수
    private Integer commentCount; // 댓글 개수
    private Integer trialCount; // 트라이얼 개수
    private Integer personalPeriod; //개인정보유효기간
    private String phone; // 연락처
    private Integer authFlag; // 인증 플래그
    private Integer judgeFlag; // 재판 가능 유저 ( 0: 미가능, 1: 가능)
    private String gender; // 성별
    private String birth; // 생년월일
    private Integer status; // 상태
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일
    private Integer snsType; // sns 타입
    private Integer point; // 포인트
    private String lawFirmName; // 로펌명
    private String tierName; // 티어명
    private String tireLevel; // 티어레벨

}
