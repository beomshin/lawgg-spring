package com.kr.lg.db.query;

import com.kr.lg.db.builder.BoardBuilder;
import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.common.enums.entity.element.DepthEnum;
import com.kr.lg.common.enums.entity.element.StatusEnum;
import com.kr.lg.model.querydsl.QBoardQ;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.kr.lg.model.querydsl.BoardQ;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.kr.lg.db.entities.QBoardTb.boardTb;
import static com.kr.lg.db.entities.QUserTb.userTb;
import static com.kr.lg.db.entities.QBoardCommentTb.boardCommentTb;
import static com.kr.lg.db.entities.QBoardAttachTb.boardAttachTb;

@Component
@RequiredArgsConstructor
@Slf4j
public class BoardQuery {

    private final JPAQueryFactory jpaQueryFactory;
    private final BoardBuilder boardBuilder;

    public JPAQuery<BoardQ> findAllBoardList(BoardLayer requestDto, Pageable pageable) { // 게시판 검색
        return jpaQueryFactory.select(
                new QBoardQ(
                        boardTb.boardId,
                        boardTb.postType,
                        boardTb.title,
                        boardTb.writer,
                        boardTb.writeDt,
                        boardTb.view,
                        boardTb.recommendCount,
                        boardTb.commentCount,
                        boardTb.writerType,
                        boardTb.lineType,
                        userTb.profile
                ))
                .from(boardTb)
                .leftJoin(userTb).on(boardTb.userTb.userId.eq(userTb.userId))
                .where(
                        boardBuilder.eqLineType(requestDto.getType()), // 라인 타입
                        boardBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword()), // 검색 조건
                        boardBuilder.eqStatus(StatusEnum.NORMAL_STATUS)
                )
                .orderBy(boardBuilder.orderTopic(requestDto.getTopic()).stream().toArray(OrderSpecifier[]::new)) // 정렬조건
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(boardTb.boardId);
    }

    public JPAQuery<Long> findAllBoardListCount(BoardLayer requestDto) { // 게시판 총 개수 조회
        return jpaQueryFactory
                .select(boardTb.count()).from(boardTb)
                .where(
                        boardBuilder.eqLineType(requestDto.getType()), // 라인 타입
                        boardBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword()), // 검색 조건
                        boardBuilder.eqStatus(StatusEnum.NORMAL_STATUS)
                );
    }

    public JPAQuery<BoardQ> findBoardDetail(BoardLayer requestDto) { // 게시판 상세 조회
        return jpaQueryFactory.select(
                new QBoardQ(
                        boardTb.boardId,
                        userTb.profile,
                        boardTb.postType,
                        boardTb.title,
                        boardTb.content,
                        boardTb.writer,
                        boardTb.writerType,
                        boardTb.writeDt,
                        boardTb.view,
                        boardTb.status,
                        boardTb.recommendCount,
                        boardTb.commentCount,
                        Expressions.as(this.getBoardCommentId(), "boardCommentId"),
                        boardTb.ip
                ))
                .from(boardTb)
                .leftJoin(userTb).on(userTb.userId.eq(boardTb.userTb.userId))
                .where(
                        boardBuilder.eqBoardId(requestDto.getId())
                )
                .groupBy(boardTb.boardId);
    }

    public JPAQuery<Long> getBoardCommentId() {
        return jpaQueryFactory.select(boardCommentTb.boardCommentId)
                .from(boardCommentTb)
                .where(boardTb.boardId.eq(boardCommentTb.boardTb.boardId),
                        boardCommentTb.depth.eq(DepthEnum.ROOT_COMMENT));
    }

    public JPAQuery<BoardQ> findBoardFiles(BoardLayer requestDto) {
        return jpaQueryFactory.select(
                    new QBoardQ(
                            boardAttachTb.boardAttachId,
                            boardAttachTb.path,
                            boardAttachTb.oriName,
                            boardAttachTb.newName,
                            boardAttachTb.size
                    )
                )
                .from(boardAttachTb)
                .where(
                        boardAttachTb.boardId.boardId.eq(requestDto.getId()),
                        boardBuilder.eqBoardAttachNormalStatus()
                );
    }

    public JPAQuery<BoardQ> findUserBoardList(BoardLayer requestDto, Pageable pageable) { // 게시판 검색
        return jpaQueryFactory.select(
                        new QBoardQ(
                                boardTb.boardId,
                                boardTb.postType,
                                boardTb.title,
                                boardTb.writer,
                                boardTb.writeDt,
                                boardTb.view,
                                boardTb.recommendCount,
                                boardTb.commentCount,
                                boardTb.writerType,
                                boardTb.lineType,
                                userTb.profile
                        ))
                .from(boardTb)
                .leftJoin(userTb).on(boardTb.userTb.userId.eq(userTb.userId))
                .where(
                        boardBuilder.eqLineType(requestDto.getType()) // 라인 타입
                        , boardBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword()) // 검색 조건
                        , boardBuilder.eqStatus(StatusEnum.NORMAL_STATUS)
                        , boardTb.userTb.userId.eq(requestDto.getUserTb().getUserId())
                )
                .orderBy(boardBuilder.orderTopic(requestDto.getTopic()).stream().toArray(OrderSpecifier[]::new)) // 정렬조건
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(boardTb.boardId);
    }

    public JPAQuery<Long> findUserBoardListCount(BoardLayer requestDto) { // 게시판 총 개수 조회
        return jpaQueryFactory
                .select(boardTb.count()).from(boardTb)
                .where(
                        boardBuilder.eqLineType(requestDto.getType())
                        , boardBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword())
                        , boardBuilder.eqStatus(StatusEnum.NORMAL_STATUS)
                        , boardTb.userTb.userId.eq(requestDto.getUserTb().getUserId())
                );
    }


    public JPAQuery<BoardQ> findLawFirmBoardList(BoardLayer requestDto, Pageable pageable) { // 게시판 검색
        return jpaQueryFactory.select(
                        new QBoardQ(
                                boardTb.boardId,
                                boardTb.postType,
                                boardTb.title,
                                boardTb.writer,
                                boardTb.writeDt,
                                boardTb.view,
                                boardTb.recommendCount,
                                boardTb.commentCount,
                                boardTb.writerType,
                                boardTb.lineType,
                                userTb.profile
                        ))
                .from(boardTb)
                .leftJoin(userTb).on(boardTb.userTb.userId.eq(userTb.userId))
                .where(boardBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword()) // 검색 조건
                        , boardBuilder.eqStatus(StatusEnum.NORMAL_STATUS)
                        , boardTb.lawFirmTb.lawFirmId.eq(requestDto.getId())
                )
                .orderBy(boardBuilder.orderTopic(requestDto.getTopic()).stream().toArray(OrderSpecifier[]::new)) // 정렬조건
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(boardTb.boardId);
    }

    public JPAQuery<Long> findLawFirmBoardListCount(BoardLayer requestDto) { // 게시판 총 개수 조회
        return jpaQueryFactory
                .select(boardTb.count()).from(boardTb)
                .where(boardBuilder.eqSubject(requestDto.getSubject(), requestDto.getKeyword())
                        , boardBuilder.eqStatus(StatusEnum.NORMAL_STATUS)
                        , boardTb.lawFirmTb.lawFirmId.eq(requestDto.getId())
                );
    }
}
