package com.kr.lg.listener;

import com.kr.lg.model.common.listener.TrialCEvent;
import com.kr.lg.module.trial.model.event.TrialCreateCountEvent;
import com.kr.lg.module.trial.model.event.TrialCountEvent;
import com.kr.lg.module.trial.model.event.TrialRecommendEvent;
import com.kr.lg.db.repositories.TrialRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.db.entities.TrialTb;
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
public class TrialListener {

    private final TrialRepository trialRepository;
    private final UserRepository userRepository;

    @Value("${spring.profiles.active}")
    private String active;


    @TransactionalEventListener
    @Async
    @Transactional
    public void increaseCount(TrialCountEvent TrialCountEvent) { // 조회수 증가
        trialRepository.viewTrial(TrialCountEvent.getTrialId()); // 조회수 증가
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void  updateTrialCommentCount(TrialCEvent TrialCEvent) {
        log.debug("[updateTrialCommentCount]");
        TrialTb trialTb = trialRepository.findLockTrial(TrialCEvent.getTrialId());
        trialRepository.updateCommentCount(trialTb.getTrialId(), Long.valueOf(TrialCEvent.getNum()));
    }

    @TransactionalEventListener
    @Async
    @Transactional
    public void updateTrialCount(TrialCreateCountEvent TrialCreateCountEvent) {
        log.debug("[updateTrialCount]");
        UserTb userTb = TrialCreateCountEvent.getUserTb();
        userRepository.updateTrialCount(userTb.getUserId(), Long.valueOf(TrialCreateCountEvent.getNum()));
    }


    @TransactionalEventListener
    @Async
    @Transactional
    public void  updateTrialRecommendCount(TrialRecommendEvent trialR) {
        log.debug("[updateTrialRecommendCount] : {}", trialR);
        TrialTb trialTb = trialRepository.findLockTrial(trialR.getTrialId());
        trialRepository.updateRecommendCount(trialTb.getTrialId(), Long.valueOf(trialR.getNum()));
    }

}
