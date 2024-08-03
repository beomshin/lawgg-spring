package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.convert.status.BoardStatusConverter;
import com.kr.lg.common.enums.convert.type.LineTypeConverter;
import com.kr.lg.common.enums.convert.type.PostTypeConverter;
import com.kr.lg.common.enums.convert.type.WriterTypeConverter;
import com.kr.lg.common.enums.entity.status.BoardStatus;
import com.kr.lg.common.enums.entity.type.LineType;
import com.kr.lg.common.enums.entity.type.PostType;
import com.kr.lg.common.enums.entity.type.WriterType;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "BoardTb")
@Table(
        name = "BoardTb",
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
public class BoardTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boardId")
    private Long boardId; // 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmTb;

    @Column(name = "password")
    private String password;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "ip")
    private String ip;
    @Column(name = "writeDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp writeDt; // 수정일

    @Column(name = "postType")
    @Convert(converter = PostTypeConverter.class)
    private PostType postType; // 게시글 타입 ( 0 : 일반, 1: 이미지, 2: 추천, 3: 베스트, 98: 이벤트, 99: 공지 )

    @Column(name = "writerType")
    @Convert(converter = WriterTypeConverter.class)
    private WriterType writerType; // 작성 타입 ( 0: 비회원, 1: 회원, 2: 로펌 )

    @Column(name = "lineType")
    @Convert(converter = LineTypeConverter.class)
    private LineType lineType; // 라인 타입 ( 0 : top , 1 : jug, 2 : mid, 3: add , 4: spt, 5: all)

    @Column(name = "commentCount")
    private Long commentCount;

    @Column(name = "recommendCount")
    private Long recommendCount;

    @Column(name = "view")
    private Long view;

    @Column(name = "report")
    private Long report;

    @Column(name = "status")
    @Convert(converter = BoardStatusConverter.class)
    private BoardStatus status; // 게시글 상태 ( 1: 정상, 2: 삭제, 9: 정지 )

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정
}
