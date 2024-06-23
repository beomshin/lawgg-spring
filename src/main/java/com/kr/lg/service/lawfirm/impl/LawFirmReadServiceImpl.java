package com.kr.lg.service.lawfirm.impl;

import com.kr.lg.dao.LawFirmDao;
import com.kr.lg.entities.LawFirmTb;
import com.kr.lg.enums.entity.element.ApplyStatusEnum;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.enums.entity.element.Status2Enum;
import com.kr.lg.repositories.LawFirmApplyRepository;
import com.kr.lg.web.common.root.DefaultResponse;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.web.common.layer.LawFLayer;
import com.kr.lg.web.net.response.lawfirm.*;
import com.kr.lg.web.querydsl.LawFirmQ;
import com.kr.lg.service.lawfirm.LawFirmReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LawFirmReadServiceImpl implements LawFirmReadService {

    private final LawFirmDao lawFirmDao;
    private final LawFirmApplyRepository lawFirmApplyRepository;

    @Override
    public DefaultResponse findAllLawFirmList(LawFLayer LawFLayer) throws LgException {
        Page<LawFirmQ> lawFirms = lawFirmDao.findAllLawFirmList(LawFLayer, PageRequest.of(LawFLayer.getPage(), LawFLayer.getPageNum()));
        return new FindALFLResponse(lawFirms);
    }

    @Override
    public DefaultResponse findAnonymousLawFirmDetail(LawFLayer LawFLayer) throws LgException {
        LawFirmQ lawFirm = lawFirmDao.findLawFirmDetail(LawFLayer).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        return new FindALFDResponse(lawFirm);
    }

    @Override
    public DefaultResponse findUserLawFirmDetail(LawFLayer LawFLayer) throws LgException {
        LawFirmQ lawFirm = lawFirmDao.findLawFirmDetail(LawFLayer).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        int result = lawFirmApplyRepository.countByLawFirmTb_LawFirmIdAndUserTb_UserIdAndStatus(lawFirm.getLawFirmId(), LawFLayer.getUserTb().getUserId(), ApplyStatusEnum.APPLY_STATUS);
        lawFirm.setApplyFlag(result > 0 ? 1 : 0); // 지원 여부
        LawFirmTb lawFirmTb = LawFLayer.getUserTb().getLawFirmId();
        if (lawFirmTb != null && lawFirmTb.getStatus() == Status2Enum.NORMAL_STATUS) {
            lawFirm.setMyLawFirmId(lawFirmTb.getLawFirmId());
            lawFirm.setIsSignLawFirmFlag(1);
            lawFirm.setIsMyLawFirmFlag(lawFirmTb.getLawFirmId().equals(LawFLayer.getId()) ? 1 : 0);
        } else {
            lawFirm.setIsSignLawFirmFlag(0);
            lawFirm.setIsMyLawFirmFlag(0);
        }
        return new FindULFDResponse(lawFirm);
    }

    @Override
    public DefaultResponse findMyLawFirm(LawFLayer LawFLayer) throws LgException {
        if (!(LawFLayer.getUserTb().getLawFirmId() != null)) throw new LgException(GlobalCode.NOT_EXIST_LAW_FIRM); // 존재하지 않는 로펌
        LawFirmQ lawFirm = lawFirmDao.findMyLawFirm(LawFLayer.getUserTb().getLawFirmId().getLawFirmId()).orElseThrow(() -> new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        return new FindLFDResponse(lawFirm);
    }

    @Override
    public DefaultResponse findApplyListMyLawFirm(LawFLayer LawFLayer) throws LgException {
        if (!(LawFLayer.getUserTb().getLawFirmId() != null)) throw new LgException(GlobalCode.NOT_EXIST_LAW_FIRM); // 존재하지 않는 로펌
        LawFLayer.setId(LawFLayer.getUserTb().getLawFirmId().getLawFirmId());
        Page<LawFirmQ> applyList = lawFirmDao.findApplyListMyLawFirm(LawFLayer, PageRequest.of(LawFLayer.getPage(), LawFLayer.getPageNum())); // 로펌 지원자 리스트 조회
        return new FindALMLFResponse(applyList);
    }

    @Override
    public DefaultResponse findUserListMyLawFirm(LawFLayer LawFLayer) throws LgException {
        if (!(LawFLayer.getUserTb().getLawFirmId() != null)) throw new LgException(GlobalCode.NOT_EXIST_LAW_FIRM); // 존재하지 않는 로펌
        Page<LawFirmQ> userList = lawFirmDao.findUserListMyLawFirm(LawFLayer, PageRequest.of(LawFLayer.getPage(), LawFLayer.getPageNum())); // 로펌 유저 리스트 조회
        return new FindULMLFResponse(userList);
    }
}
