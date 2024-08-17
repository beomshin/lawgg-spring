package com.kr.lg.module.lawfirm.service.impl;

import com.kr.lg.db.entities.LawFirmApplyTb;
import com.kr.lg.db.repositories.LawFirmApplyRepository;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.exception.LawFirmResultCode;
import com.kr.lg.module.lawfirm.model.dto.LawFirmEnrollDto;
import com.kr.lg.module.lawfirm.service.LawFirmEnrollService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LawFirmEnrollServiceImpl implements LawFirmEnrollService {

    private final LawFirmApplyRepository lawFirmApplyRepository;

    @Override
    public void saveLawFirmApply(LawFirmEnrollDto enrollDto) throws LawFirmException {
        try {
            log.info("▶ [로펌] 로펌 등록");
            LawFirmApplyTb applyTb = LawFirmApplyTb.builder()
                    .lawFirmTb(enrollDto.getLawFirmTb())
                    .userTb(enrollDto.getUserTb())
                    .title("[로펌 신청]")
                    .introduction("[로펌 신청]")
                    .build();
            lawFirmApplyRepository.save(applyTb); // 로펌 신청
        } catch (Exception e) {
            log.error("", e);
            throw new LawFirmException(LawFirmResultCode.FAIL_APPLY_LAW_FIRM);
        }
    }

}
