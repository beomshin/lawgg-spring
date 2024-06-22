package com.kr.lg.listener;

import com.kr.lg.model.web.common.listener.TrialCEvent;
import com.kr.lg.model.web.common.listener.TrialCNTEvent;
import com.kr.lg.model.web.common.listener.TrialCTEvent;
import com.kr.lg.model.web.common.listener.TrialREvent;
import com.kr.lg.repositories.TrialRepository;
import com.kr.lg.repositories.UserRepository;
import com.kr.lg.entities.TrialTb;
import com.kr.lg.entities.UserTb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.TimeUnit;

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
    public void increaseCount(TrialCTEvent TrialCTEvent) { // 조회수 증가
        trialRepository.viewTrial(TrialCTEvent.getTrialId()); // 조회수 증가
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
    public void updateTrialCount(TrialCNTEvent TrialCNTEvent) {
        log.debug("[updateTrialCount]");
        UserTb userTb = TrialCNTEvent.getUserTb();
        userRepository.updateTrialCount(userTb.getUserId(), Long.valueOf(TrialCNTEvent.getNum()));
    }


    @TransactionalEventListener
    @Async
    @Transactional
    public void  updateTrialRecommendCount(TrialREvent trialR) {
        log.debug("[updateTrialRecommendCount] : {}", trialR);
        TrialTb trialTb = trialRepository.findLockTrial(trialR.getTrialId());
        trialRepository.updateRecommendCount(trialTb.getTrialId(), Long.valueOf(trialR.getNum()));
    }

}
