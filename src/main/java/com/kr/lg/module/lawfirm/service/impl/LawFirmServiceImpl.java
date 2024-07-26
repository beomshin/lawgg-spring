package com.kr.lg.module.lawfirm.service.impl;

import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.LawFirmApplyRepository;
import com.kr.lg.enums.ApplyStatusEnum;
import com.kr.lg.module.lawfirm.exception.LawFirmResultCode;
import com.kr.lg.module.lawfirm.model.dto.LawFirmEnrollDto;
import com.kr.lg.module.lawfirm.model.req.ApplyLawFirmRequest;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.service.LawFirmEnrollService;
import com.kr.lg.module.lawfirm.service.LawFirmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LawFirmServiceImpl implements LawFirmService {

    private final LawFirmEnrollService lawFirmEnrollService;
    private final LawFirmApplyRepository lawFirmApplyRepository;

    @Override
    public void applyLawFirm(ApplyLawFirmRequest request, UserTb userTb) throws LawFirmException {
        log.info("▶ [로펌] applyLawFirm 메소드 실행");

        if (userTb.getLawFirmId() != null) throw new LawFirmException(LawFirmResultCode.ALREADY_JOIN_USER); // 이미 로펌을 가지고 있는 경우
        int isApply = lawFirmApplyRepository.countByLawFirmTb_LawFirmIdAndUserTb_UserIdAndStatus(request.getId(), userTb.getUserId(), ApplyStatusEnum.APPLY_STATUS);
        if (isApply > 0) throw new LawFirmException(LawFirmResultCode.ALREADY_APPLY_USER); // 이미 로펌을 가지고 있는 경우
        LawFirmEnrollDto enrollDto =  LawFirmEnrollDto.builder()
                .lawFirmTb(LawFirmTb.builder().lawFirmId(request.getId()).build())
                .userTb(userTb)
                .title(request.getTitle())
                .introduction(request.getIntroduction())
                .build();
        lawFirmEnrollService.saveLawFirmApply(enrollDto); // 로펌 신청
    }

}
