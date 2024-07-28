package com.kr.lg.module.trial.service.impl;

import com.kr.lg.db.entities.TrialVoteTb;
import com.kr.lg.db.repositories.TrialVoteRepository;
import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.exception.TrialResultCode;
import com.kr.lg.module.trial.model.dto.TrialVoteDto;
import com.kr.lg.module.trial.service.TrialVoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrialVoteServiceImpl implements TrialVoteService {

    private final TrialVoteRepository trialVoteRepository;

    @Override
    @Transactional
    public void voteTrial(TrialVoteDto voteDto) throws TrialException {
        try {
            log.info("▶ [트라이얼] 트라이얼 투표");
            TrialVoteTb voteTb = TrialVoteTb.builder()
                    .userTb(voteDto.getUserTb())
                    .trialTb(voteDto.getTrialTb())
                    .precedent(voteDto.getPrecedent())
                    .build();
            trialVoteRepository.save(voteTb);
        } catch (Exception e) {
            log.error("", e);
            throw new TrialException(TrialResultCode.FAIL_VOTE_TRIAL);
        }
    }

    @Override
    @Transactional
    public void changeVoteTrial(TrialVoteDto voteDto) throws TrialException {
        try {
            log.info("▶ [트라이얼] 트라이얼 변경");
            trialVoteRepository.updatePrecedent(voteDto.getTrialVoteId(), voteDto.getPrecedent()); // 투표 변경
        } catch (Exception e) {
            log.error("", e);
            throw new TrialException(TrialResultCode.FAIL_CHANGE_VOTE_TRIAL);
        }
    }
}
