package com.kr.lg.module.trial.service;

import com.kr.lg.module.trial.exception.TrialException;
import com.kr.lg.module.trial.model.dto.TrialVoteDto;

public interface TrialVoteService {

    void voteTrial(TrialVoteDto voteDto) throws TrialException;
    void changeVoteTrial(TrialVoteDto voteDto) throws  TrialException;
}
