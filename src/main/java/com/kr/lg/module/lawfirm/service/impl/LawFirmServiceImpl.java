package com.kr.lg.module.lawfirm.service.impl;

import com.kr.lg.common.enums.logic.LawFirmTopic;
import com.kr.lg.common.enums.logic.TrialTopic;
import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.db.entities.UserTb;
import com.kr.lg.db.repositories.LawFirmApplyRepository;
import com.kr.lg.common.enums.entity.status.ApplyStatus;
import com.kr.lg.common.enums.entity.status.LawFirmStatus;
import com.kr.lg.module.lawfirm.exception.LawFirmResultCode;
import com.kr.lg.module.lawfirm.model.dto.LawFirmEnrollDto;
import com.kr.lg.module.lawfirm.model.entry.LawFirmBoardEntry;
import com.kr.lg.module.lawfirm.model.entry.LawFirmEntry;
import com.kr.lg.module.lawfirm.model.mapper.FindLawFirmParamData;
import com.kr.lg.module.lawfirm.model.req.*;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.service.LawFirmDeleteService;
import com.kr.lg.module.lawfirm.service.LawFirmEnrollService;
import com.kr.lg.module.lawfirm.service.LawFirmFindService;
import com.kr.lg.module.lawfirm.service.LawFirmService;
import com.kr.lg.module.lawfirm.sort.LawFirmSort;
import com.kr.lg.model.mapper.LawFirmParam;
import com.kr.lg.model.mapper.MapperParam;
import com.kr.lg.module.trial.sort.TrialSort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LawFirmServiceImpl implements LawFirmService {

    private final LawFirmEnrollService lawFirmEnrollService;
    private final LawFirmDeleteService lawFirmDeleteService;
    private final LawFirmFindService lawFirmFindService;
    private final LawFirmApplyRepository lawFirmApplyRepository;

    @Override
    public void applyLawFirm(ApplyLawFirmRequest request, UserTb userTb) throws LawFirmException {
        log.info("▶ [로펌] applyLawFirm 메소드 실행");

        if (userTb.getLawFirmTb() != null) throw new LawFirmException(LawFirmResultCode.ALREADY_JOIN_USER); // 이미 로펌을 가지고 있는 경우
        int isApply = lawFirmApplyRepository.countByLawFirmTb_LawFirmIdAndUserTb_UserIdAndStatus(request.getId(), userTb.getUserId(), ApplyStatus.APPLY_STATUS);
        if (isApply > 0) throw new LawFirmException(LawFirmResultCode.ALREADY_APPLY_USER); // 이미 로펌을 가지고 있는 경우
        LawFirmEnrollDto enrollDto =  LawFirmEnrollDto.builder()
                .lawFirmTb(LawFirmTb.builder().lawFirmId(request.getId()).build())
                .userTb(userTb)
                .title(request.getTitle())
                .introduction(request.getIntroduction())
                .build();
        lawFirmEnrollService.saveLawFirmApply(enrollDto); // 로펌 신청
    }

    @Override
    @Transactional
    public void quitLawFirm(QuitLawFirmRequest request, UserTb userTb) throws LawFirmException {
        log.info("▶ [로펌] quitLawFirm 메소드 실행");

        if (userTb.getLawFirmTb() == null || !Objects.equals(userTb.getLawFirmTb().getLawFirmId(), request.getId())) {
            throw new LawFirmException(LawFirmResultCode.FAIL_QUIT_LAW_FIRM);
        }
        lawFirmDeleteService.quitLawFirm(userTb.getUserId());
    }

    @Override
    @Transactional
    public void cancelApplyLawFirm(CancelApplyLawFirmRequest request, UserTb userTb) throws LawFirmException {
        log.info("▶ [로펌] cancelApplyLawFirm 메소드 실행");

        lawFirmDeleteService.cancelApplyLawFirm(request.getId(), userTb.getUserId());
    }

    @Override
    public Page<LawFirmEntry> findLawFirms(FindLawFirmsRequest request) throws LawFirmException {
        log.info("▶ [로펌] findLawFirms 메소드 실행");

        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), LawFirmSort.idDesc());
        MapperParam param = FindLawFirmParamData.builder()
                .keyword(request.getKeyword())
                .subject(request.getSubject())
                .build();
        return lawFirmFindService.findLawFirms(new LawFirmParam<>(param, pageable));
    }

    @Override
    public LawFirmEntry findLawFirmWithNotLogin(long id) throws LawFirmException {
        log.info("▶ [로펌] findLawFirmWithNotLogin 메소드 실행");

        FindLawFirmParamData param = FindLawFirmParamData.builder()
                .lawFirmId(id)
                .build();
        return lawFirmFindService.findLawFirm(param);
    }

    @Override
    @Transactional
    public LawFirmEntry findLawFirmWithLogin(long id, UserTb userTb) throws LawFirmException {
        log.info("▶ [로펌] findLawFirmWithLogin 메소드 실행");

        FindLawFirmParamData param = FindLawFirmParamData.builder()
                .lawFirmId(id)
                .build();
        LawFirmEntry entry = lawFirmFindService.findLawFirm(param);
        int result = lawFirmApplyRepository.countByLawFirmTb_LawFirmIdAndUserTb_UserIdAndStatus(id, userTb.getUserId(), ApplyStatus.APPLY_STATUS);
        entry.setApplyFlag(result > 0 ? 1 : 0); // 지원 여부
        LawFirmTb lawFirmTb = userTb.getLawFirmTb();
        if (lawFirmTb != null && lawFirmTb.getStatus() == LawFirmStatus.NORMAL_STATUS) { // 로그인 유저 가입 로펌이 있는 경우
            entry.setMyLawFirmId(lawFirmTb.getLawFirmId()); // 내로펌가기 (id 제공)
            entry.setIsSignLawFirmFlag(1); // 다른 로펌 가입여부 (0: 미가입, 1:가입)
            entry.setIsMyLawFirmFlag(lawFirmTb.getLawFirmId() == id ? 1 : 0); // 조회 로펌이 로그인 유저 가입 로펌과 동일 확인
        } else {
            entry.setIsSignLawFirmFlag(0); // 기본값 0 설정
            entry.setIsMyLawFirmFlag(0); // 기본값 0 설정
        }
        return entry;
    }

    @Override
    public Page<LawFirmBoardEntry> findLawFirmBoard(FindLawFirmsBoardRequest request, long lawFirmId) throws LawFirmException {
        Pageable pageable = PageRequest.of(request.getPage(), request.getPageNum(), getSort(request.getTopic())); // pageable 생성
        MapperParam param = FindLawFirmParamData.builder()
                .lawFirmId(lawFirmId)
                .keyword(request.getKeyword())
                .subject(request.getSubject())
                .build();
        return lawFirmFindService.findLawFirmBoard(new LawFirmParam<>(param, pageable));
    }

    private Sort getSort(int topic) {
        if (LawFirmTopic.NEW_TOPIC == LawFirmTopic.of(topic)) {
            return LawFirmSort.writeDtDesc();
        } else if (LawFirmTopic.VIEW_TOPIC == LawFirmTopic.of(topic)) {
            return LawFirmSort.viewDesc();
        }  else {
            return LawFirmSort.hotDesc();
        }
    }
}
