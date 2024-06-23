package com.kr.lg.listener;

import com.kr.lg.web.common.listener.BoardCEvent;
import com.kr.lg.web.common.listener.BoardCNTEvent;
import com.kr.lg.web.common.listener.BoardCTEvent;
import com.kr.lg.web.common.listener.BoardREvent;
import com.kr.lg.db.repositories.BoardRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.db.entities.BoardTb;
import com.kr.lg.db.entities.UserTb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.profiles.active}")
    private String active;

    @TransactionalEventListener
    @Async
    @Transactional
    public void increaseCount(BoardCTEvent BoardCTEvent) { // 조회수 증가
            boardRepository.viewBoard(BoardCTEvent.getBoardId()); // 조회수 증가
//        }
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void  updateBoardCommentCount(BoardCEvent BoardCEvent) {
        BoardTb boardTb = boardRepository.findLockBoard(BoardCEvent.getBoardId());
        boardRepository.updateCommentCount(boardTb.getBoardId(), Long.valueOf(BoardCEvent.getNum()));
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void updateBoardCount(BoardCNTEvent BoardCNTEvent) {
        log.debug("[updateBoardCount]");
        UserTb userTb = BoardCNTEvent.getUserTb();
        userRepository.updateBoardCount(userTb.getUserId(), Long.valueOf(BoardCNTEvent.getNum()));
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void  updateBoardRecommendCount(BoardREvent BoardREvent) {
        BoardTb boardTb = boardRepository.findLockBoard(BoardREvent.getBoardId());
        boardRepository.updateRecommendCount(boardTb.getBoardId(), Long.valueOf(BoardREvent.getNum()));
    }
}
