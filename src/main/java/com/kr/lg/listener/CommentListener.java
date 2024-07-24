package com.kr.lg.listener;

import com.kr.lg.module.comment.model.dto.UserCommentCreateCountEvent;
import com.kr.lg.db.repositories.UserRepository;
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
public class CommentListener {

    private final UserRepository userRepository;

    @TransactionalEventListener
    @Async
    @Transactional
    public void  updateBoardCommentCount(UserCommentCreateCountEvent UserCommentCreateCountEvent) {
        log.debug("[updateBoardCommentCount]");
        UserTb userTb = UserCommentCreateCountEvent.getUserTb();
        userRepository.updateCommentCount(userTb.getUserId(), Long.valueOf(UserCommentCreateCountEvent.getNum()));
    }
}
