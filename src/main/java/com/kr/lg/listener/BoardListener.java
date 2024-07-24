package com.kr.lg.listener;

import com.kr.lg.module.comment.model.dto.BoardCommentCreateCountEvent;
import com.kr.lg.module.board.model.dto.BoardCreateCountEvent;
import com.kr.lg.module.board.model.dto.BoardCountEventDto;
import com.kr.lg.module.board.model.dto.BoardRecommendEventDto;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @TransactionalEventListener
    @Async
    @Transactional
    public synchronized void increaseCount(BoardCountEventDto BoardCountEventDto) {
            boardRepository.increaseCount(BoardCountEventDto.getBoardId()); // 조회수 증가
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void  updateBoardCommentCount(BoardCommentCreateCountEvent BoardCommentCreateCountEvent) {
        BoardTb boardTb = boardRepository.findLockBoard(BoardCommentCreateCountEvent.getBoardId());
        boardRepository.updateCommentCount(boardTb.getBoardId(), Long.valueOf(BoardCommentCreateCountEvent.getNum()));
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void updateBoardCount(BoardCreateCountEvent BoardCreateCountEvent) {
        log.info("[]");
        UserTb userTb = BoardCreateCountEvent.getUserTb();
        userRepository.updateBoardCount(userTb.getUserId(), Long.valueOf(BoardCreateCountEvent.getNum()));
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void  updateBoardRecommendCount(BoardRecommendEventDto BoardRecommendEventDto) {
        BoardTb boardTb = boardRepository.findLockBoard(BoardRecommendEventDto.getBoardId());
        boardRepository.updateRecommendCount(boardTb.getBoardId(), Long.valueOf(BoardRecommendEventDto.getNum()));
    }
}
