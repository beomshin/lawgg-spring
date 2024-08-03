package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.convert.crypt.DataBaseAESCryptConverter;
import com.kr.lg.common.enums.convert.status.VerificationStatusConverter;
import com.kr.lg.common.enums.entity.status.VerificationStatus;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;

@Entity(name = "MailTb")
@Table(
        name = "MailTb",
        uniqueConstraints={
                @UniqueConstraint(
                        columnNames={}
                )
        }
)
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class MailTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mailId")
    private Long mailId;

    @Column(name = "txId")
    private String txId;

    @Column(name = "receiver")
    @Convert(converter = DataBaseAESCryptConverter.class)
    private String receiver;

    @Column(name = "code")
    private String code;

    @Column(name = "verification")
    @Convert(converter = VerificationStatusConverter.class)
    private VerificationStatus verification; // 이메일 인증 상태 ( 0 : 미완료 , 1: 완료 )

    @Column(name = "expired")
    private Date expired;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp modDt; // 수정일

}
