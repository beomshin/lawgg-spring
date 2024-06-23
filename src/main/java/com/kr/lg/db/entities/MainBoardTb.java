package com.kr.lg.db.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kr.lg.common.enums.entity.converters.*;
import com.kr.lg.common.enums.entity.element.LineEnum;
import com.kr.lg.common.enums.entity.element.PostEnum;
import com.kr.lg.common.enums.entity.element.StatusEnum;
import com.kr.lg.common.enums.entity.element.WriterEnum;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "MainBoardTb")
@Table(
        name = "MainBoardTb",
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
public class MainBoardTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mainBoardId")
    private Long mainBoardId; // 식별자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserTb userTb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawFirmId")
    private LawFirmTb lawFirmTb;

    @Column(name = "boardId")
    private Long boardId;

    @Column(name = "password")
    private String password;

    @Column(name = "title")
    @Convert(converter = EmojiConverter.class)
    private String title;

    @Column(name = "content")
    @Convert(converter = EmojiConverter.class)
    private String content;

    @Column(name = "writer")
    private String writer;

    @Column(name = "ip")
    private String ip;
    @Column(name = "writeDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp writeDt; // 수정일

    @Column(name = "postType")
    @Convert(converter = PostEnumConverter.class)
    private PostEnum postType;

    @Column(name = "writerType")
    @Convert(converter = WriterEnumConverter.class)
    private WriterEnum writerType;

    @Column(name = "lineType")
    @Convert(converter = LineEnumConverter.class)
    private LineEnum lineType;

    @Column(name = "commentCount")
    private Long commentCount;

    @Column(name = "recommendCount")
    private Long recommendCount;

    @Column(name = "view")
    private Long view;

    @Column(name = "report")
    private Long report;

    @Column(name = "status")
    @Convert(converter = StatusEnumConverter.class)
    private StatusEnum status;

    @Column(name = "regDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDt; // 등록일

    @Column(name = "modDt")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp modDt; // 수정일

}
