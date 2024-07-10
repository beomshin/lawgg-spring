package com.kr.lg.db.query;

import com.kr.lg.model.common.layer.BoardLayer;
import com.kr.lg.enums.DepthEnum;
import com.kr.lg.enums.StatusEnum;
import com.kr.lg.model.querydsl.BoardQ;
import com.kr.lg.model.querydsl.QBoardQ;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.kr.lg.db.entities.QBoardCommentTb.boardCommentTb;

@Component
@RequiredArgsConstructor
@Slf4j
public class BoardCommentQuery {

    private final JPAQueryFactory jpaQueryFactory;

    public JPAQuery<Long> findRootComment(Long boardId) {
        return jpaQueryFactory
                .select(boardCommentTb.boardCommentId)
                .from(boardCommentTb)
                .where(
                        boardCommentTb.boardTb.boardId.eq(boardId),
                        boardCommentTb.depth.eq(DepthEnum.ROOT_COMMENT)
                );
    }

    public JPAQuery<BoardQ> findAnonymousParentCommentBoard(BoardLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QBoardQ(
                        boardCommentTb.boardCommentId,
                        boardCommentTb.parentId,
                        boardCommentTb.order,
                        boardCommentTb.depth,
                        boardCommentTb.writer,
                        boardCommentTb.content,
                        boardCommentTb.status,
                        boardCommentTb.regDt,
                        new CaseBuilder().when(boardCommentTb.userTb.isNull()).then(1).otherwise(0).as("anonym")
                ))
                .from(boardCommentTb)
                .where(
                        boardCommentTb.boardTb.boardId.eq(requestDto.getId()),
                        boardCommentTb.depth.eq(requestDto.getDepth()),
                        boardCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(boardCommentTb.boardCommentId.asc());
    }

    public JPAQuery<Long> findAnonymousParentCommentBoardCount(BoardLayer requestDto) {
        return jpaQueryFactory
                .select(boardCommentTb.count())
                .from(boardCommentTb)
                .where(
                        boardCommentTb.boardTb.boardId.eq(requestDto.getId()),
                        boardCommentTb.depth.eq(requestDto.getDepth()),
                        boardCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                );
    }

    public JPAQuery<BoardQ> findUserParentCommentBoard(BoardLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QBoardQ(
                        boardCommentTb.boardCommentId,
                        boardCommentTb.parentId,
                        boardCommentTb.order,
                        boardCommentTb.depth,
                        boardCommentTb.writer,
                        boardCommentTb.content,
                        boardCommentTb.status,
                        boardCommentTb.regDt,
                        new CaseBuilder().when(boardCommentTb.userTb.userId.eq(requestDto.getUserTb().getUserId())).then(1).otherwise(0).as("created"),
                        new CaseBuilder().when(boardCommentTb.userTb.isNull()).then(1).otherwise(0).as("anonym")
                ))
                .from(boardCommentTb)
                .where(
                        boardCommentTb.boardTb.boardId.eq(requestDto.getId()),
                        boardCommentTb.depth.eq(requestDto.getDepth()),
                        boardCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(boardCommentTb.boardCommentId.asc());
    }

    public JPAQuery<Long> findUserParentCommentBoardCount(BoardLayer requestDto) {
        return jpaQueryFactory
                .select(boardCommentTb.count())
                .from(boardCommentTb)
                .where(
                        boardCommentTb.boardTb.boardId.eq(requestDto.getId()),
                        boardCommentTb.depth.eq(requestDto.getDepth()),
                        boardCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                );
    }

    public JPAQuery<BoardQ> findAnonymousChildrenCommentBoard(BoardLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QBoardQ(
                        boardCommentTb.boardCommentId,
                        boardCommentTb.parentId,
                        boardCommentTb.order,
                        boardCommentTb.depth,
                        boardCommentTb.writer,
                        boardCommentTb.content,
                        boardCommentTb.status,
                        boardCommentTb.regDt,
                        new CaseBuilder().when(boardCommentTb.userTb.isNull()).then(1).otherwise(0).as("anonym")
                ))
                .from(boardCommentTb)
                .where(
                        boardCommentTb.parentId.eq(requestDto.getId()),
                        boardCommentTb.depth.eq(requestDto.getDepth()),
                        boardCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(boardCommentTb.boardCommentId.asc());
    }

    public JPAQuery<Long> findAnonymousChildrenCommentBoardCount(BoardLayer requestDto) {
        return jpaQueryFactory
                .select(boardCommentTb.count())
                .from(boardCommentTb)
                .where(
                        boardCommentTb.parentId.eq(requestDto.getId()),
                        boardCommentTb.depth.eq(requestDto.getDepth()),
                        boardCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                );
    }

    public JPAQuery<BoardQ> findUserChildrenCommentBoard(BoardLayer requestDto, Pageable pageable) {
        return jpaQueryFactory
                .select(new QBoardQ(
                        boardCommentTb.boardCommentId,
                        boardCommentTb.parentId,
                        boardCommentTb.order,
                        boardCommentTb.depth,
                        boardCommentTb.writer,
                        boardCommentTb.content,
                        boardCommentTb.status,
                        boardCommentTb.regDt,
                        new CaseBuilder().when(boardCommentTb.userTb.userId.eq(requestDto.getUserTb().getUserId())).then(1).otherwise(0).as("created"),
                        new CaseBuilder().when(boardCommentTb.userTb.isNull()).then(1).otherwise(0).as("anonym")
                ))
                .from(boardCommentTb)
                .where(
                        boardCommentTb.parentId.eq(requestDto.getId()),
                        boardCommentTb.depth.eq(requestDto.getDepth()),
                        boardCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(boardCommentTb.boardCommentId.asc());
    }

    public JPAQuery<Long> findUserChildrenCommentBoardCount(BoardLayer requestDto) {
        return jpaQueryFactory
                .select(boardCommentTb.count())
                .from(boardCommentTb)
                .where(
                        boardCommentTb.parentId.eq(requestDto.getId()),
                        boardCommentTb.depth.eq(requestDto.getDepth()),
                        boardCommentTb.status.eq(StatusEnum.NORMAL_STATUS)
                );
    }

}
