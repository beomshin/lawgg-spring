package com.kr.lg.module.lawfirm.service.impl;

import com.kr.lg.enums.ApplyStatusEnum;
import com.kr.lg.db.repositories.LawFirmApplyRepository;
import com.kr.lg.db.repositories.UserRepository;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.exception.LawFirmResultCode;
import com.kr.lg.module.lawfirm.service.LawFirmDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LawFirmDeleteServiceImpl implements LawFirmDeleteService {

    private final UserRepository userRepository;
    private final LawFirmApplyRepository lawFirmApplyRepository;


    @Override
    @Transactional
    public void quitLawFirm(long userId) throws LawFirmException {
        try {
            log.info("▶ [로펌] 로펌 탈퇴");
            userRepository.deleteLawFirm(userId); // 로펌 탈퇴
        } catch (Exception e) {
            log.error("", e);
            throw new LawFirmException(LawFirmResultCode.ALREADY_APPLY_USER);
        }
    }

    @Override
    @Transactional
    public void cancelApplyLawFirm(long lawFirmId, long userId) throws LawFirmException {
        try {
            log.info("▶ [로펌] 신청 취소");
            lawFirmApplyRepository.cancelApplyLawFirm(lawFirmId, userId, ApplyStatusEnum.CANCEL_STATUS);
        } catch (Exception e) {
            log.error("", e);
            throw new LawFirmException(LawFirmResultCode.ALREADY_APPLY_USER);
        }
    }

}
