package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.convert.crypt.DataBaseAESCryptConverter;
import com.kr.lg.common.enums.convert.flag.AuthFlagConverter;
import com.kr.lg.common.enums.convert.flag.JudgeUserFlagConverter;
import com.kr.lg.common.enums.convert.status.DeleteStatusConverter;
import com.kr.lg.common.enums.convert.status.UserStatusConverter;
import com.kr.lg.common.enums.convert.type.SnsTypeConverter;
import com.kr.lg.common.enums.entity.flag.AuthFlag;
import com.kr.lg.common.enums.entity.flag.JudgeUserFlag;
import com.kr.lg.common.enums.entity.status.DeleteStatus;
import com.kr.lg.common.enums.entity.type.SnsType;
import com.kr.lg.common.enums.entity.status.UserStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "UserTb")
@Table(
        name = "UserTb",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={"loginId"}
                )
        }
)
@Getter
@ToString(exclude = {"tierTb", "lawFirmTb"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class UserTb { // 관리자 테이블

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId; // 식별자

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tierId")
    private TierTb tierTb;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmTb;

    @Column(name = "loginId")
    private String loginId;

    @Column(name = "snsId")
    private String snsId;

    @Column(name = "impUid")
    private String impUid;

    @Column(name = "ci")
    private String ci;

    @Column(name = "di")
    private String di;

    @Column(name = "birth")
    private String birth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "password")
    private String password;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "hashEmail")
    private String hashEmail;

    @Column(name = "phone")
    @Convert(converter = DataBaseAESCryptConverter.class)
    private String phone;

    @Column(name = "profile")
    private String profile;

    @Column(name = "point")
    private Long point;

    @Column(name = "boardCount")
    private Long boardCount;

    @Column(name = "trialCount")
    private Long trialCount;

    @Column(name = "commentCount")
    private Long commentCount;

    @Column(name = "snsType")
    @Convert(converter = SnsTypeConverter.class)
    private SnsType snsType;

    @Column(name = "personalPeriod")
    private Integer personalPeriod;

    @Column(name = "delFlag")
    @Convert(converter = DeleteStatusConverter.class)
    private DeleteStatus delFlag; // 삭제 플래그 ( 0: 미삭제, 1: 삭제)

    @Column(name = "authFlag")
    @Convert(converter = AuthFlagConverter.class)
    private AuthFlag authFlag; // 인증 플래그 ( 0: 미인증, 1: 인증)

    @Column(name = "judgeFlag")
    @Convert(converter = JudgeUserFlagConverter.class)
    private JudgeUserFlag judgeFlag; // 재판 가능 유저 플래그 ( 0: 미가능, 1: 가능)

    @Column(name = "status")
    @Convert(converter = UserStatusConverter.class)
    private UserStatus status; // 유저 상태 ( 1: 정상, 9: 탈퇴)

    @Column(name = "authDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp authDt; // 등록일

    @Column(name = "lawFirmEnrollDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp lawFirmEnrollDt; // 등록일

    @Column(name = "regDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일

    public void quitLawFirm() {
        this.lawFirmEnrollDt = null;
        this.lawFirmTb = null;
    }

}
