package com.kr.lg.listener;

import com.kr.lg.module.comment.model.event.BoardCommentCreateCountEvent;
import com.kr.lg.module.board.model.event.UserBoardCreateCountEvent;
import com.kr.lg.module.board.model.event.BoardCountEvent;
import com.kr.lg.module.board.model.event.BoardRecommendEvent;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
public class BoardListener {


    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @EventListener
    @Async
    @Transactional
    public synchronized void increaseCount(BoardCountEvent BoardCountEvent) {
        log.info("[포지션 게시판 조회수 증가]");
        boardRepository.increaseCount(BoardCountEvent.getBoardId()); // 조회수 증가
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void  updateBoardCommentCount(BoardCommentCreateCountEvent boardCommentCreateCountEvent) {
        log.info("[포지션 게시판 댓글 수 변경] id {}, count {}", boardCommentCreateCountEvent.getBoardId(), boardCommentCreateCountEvent.getNum());
        BoardTb boardTb = boardRepository.findLockBoard(boardCommentCreateCountEvent.getBoardId());
        boardRepository.updateCommentCount(boardTb.getBoardId(), boardCommentCreateCountEvent.getNum());
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void updateUserBoardCount(UserBoardCreateCountEvent UserBoardCreateCountEvent) {
        log.info("[포지션 게시판 유저 댓글 수 변경] id {}, count {}", UserBoardCreateCountEvent.getUserTb().getUserId(), UserBoardCreateCountEvent.getNum());
        UserTb userTb = UserBoardCreateCountEvent.getUserTb();
        userRepository.updateBoardCount(userTb.getUserId(), Long.valueOf(UserBoardCreateCountEvent.getNum()));
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void  updateBoardRecommendCount(BoardRecommendEvent BoardRecommendEvent) {
        log.info("[포지션 게시판 추천 수 변경] id {}, count {}", BoardRecommendEvent.getBoardId(), BoardRecommendEvent.getNum());
        BoardTb boardTb = boardRepository.findLockBoard(BoardRecommendEvent.getBoardId());
        boardRepository.updateRecommendCount(boardTb.getBoardId(), Long.valueOf(BoardRecommendEvent.getNum()));
    }
}
