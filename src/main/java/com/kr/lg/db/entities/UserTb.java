package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.converters.*;
import com.kr.lg.enums.*;
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
@ToString(exclude = {"tierId", "lawFirmId"})
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
    private TierTb tierId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmId;

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
    @Convert(converter = AESCryptConverter.class)
    private String email;

    @Column(name = "hashEmail")
    private String hashEmail;

    @Column(name = "phone")
    @Convert(converter = AESCryptConverter.class)
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
    @Convert(converter = SnsEnumConverter.class)
    private SnsEnum snsType;

    @Column(name = "personalPeriod")
    private Integer personalPeriod;

    @Column(name = "delFlag")
    @Convert(converter = DelEnumConverter.class)
    private DelEnum delFlag;

    @Column(name = "authFlag")
    @Convert(converter = AuthEnumConverter.class)
    private AuthEnum authFlag;

    @Column(name = "judgeFlag")
    @Convert(converter = JudgeEnumConverter.class)
    private JudgeEnum judgeFlag;

    @Column(name = "status")
    @Convert(converter = Status3EnumConverter.class)
    private Status3Enum status;

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

}
