package com.kr.lg.service.trial.base;

import com.kr.lg.common.exception.LgException;
import com.kr.lg.model.web.common.root.DefaultResponse;
import com.kr.lg.model.web.common.layer.TrialLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TrialFindService {

    DefaultResponse findAllListTrial(TrialLayer requestDto) throws LgException;
    DefaultResponse findUserListTrial(TrialLayer requestDto) throws LgException;
    DefaultResponse findLawFirmListTrial(TrialLayer requestDto) throws LgException;
    DefaultResponse findAnonymousDetailTrial(TrialLayer requestDto) throws LgException;
    DefaultResponse findUserDetailTrial(TrialLayer requestDto) throws LgException;
}
