package com.kr.lg.service.lawfirm.impl;

import com.kr.lg.entities.LawFirmTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.enums.entity.element.ApplyStatusEnum;
import com.kr.lg.enums.entity.element.Status2Enum;
import com.kr.lg.repositories.LawFirmApplyRepository;
import com.kr.lg.repositories.LawFirmRepository;
import com.kr.lg.repositories.UserRepository;
import com.kr.lg.web.common.global.GlobalCode;
import com.kr.lg.utils.LawFirmUtils;
import com.kr.lg.web.common.layer.LawFLayer;
import com.kr.lg.service.lawfirm.LawFirmDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LawFirmDeleteServiceImpl implements LawFirmDeleteService {

    private final LawFirmRepository lawFirmRepository;
    private final UserRepository userRepository;
    private final LawFirmUtils lawFirmUtils;
    private final LawFirmApplyRepository lawFirmApplyRepository;

    @Override
    public void deleteLawFirm(LawFLayer LawFLayer) throws LgException {
        LawFirmTb lawFirmTb = lawFirmRepository.findById(LawFLayer.getId()).orElseThrow(()->new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        lawFirmUtils.isLawFirmManager(lawFirmTb.getUserTb(), LawFLayer.getUserTb()); // 로펌 매니저 검사
        if (lawFirmTb.getStatus().equals(Status2Enum.DELETE_STATUS)) throw new LgException(GlobalCode.DELETE_LAW_FIRM);
        lawFirmRepository.updateStatus(Status2Enum.DELETE_STATUS, lawFirmTb.getLawFirmId());
    }

    @Override
    public int quitLawFirm(LawFLayer LawFLayer) throws LgException {
        lawFirmUtils.isRightDeleteUser(LawFLayer.getUserTb(), LawFLayer.getId()); // 올바른 삭제 유저 체크
        return userRepository.deleteLawFirm(LawFLayer.getUserTb().getUserId()); // 로펌 탈퇴
    }

    @Override
    public void userDeleteLawFirm(LawFLayer LawFLayer) throws LgException {
        LawFirmTb lawFirmTb = lawFirmRepository.findById(LawFLayer.getId()).orElseThrow(()->new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        lawFirmUtils.isLawFirmManager(lawFirmTb.getUserTb(), LawFLayer.getUserTb()); // 로펌 매니저 검사
        userRepository.deleteLawFirm(LawFLayer.getUserId()); // 로펌 탈퇴 시키기
    }

    @Override
    public int userCancelLawFirm(LawFLayer LawFLayer) throws LgException {
        return lawFirmApplyRepository.updateApplyStatus(ApplyStatusEnum.CANCEL_STATUS, LawFLayer.getId(), LawFLayer.getUserTb().getUserId());
    }

}
