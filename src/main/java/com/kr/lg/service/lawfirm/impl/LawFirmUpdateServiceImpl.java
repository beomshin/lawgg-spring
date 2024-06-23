package com.kr.lg.service.lawfirm.impl;

import com.kr.lg.db.entities.LawFirmTb;
import com.kr.lg.common.exception.LgException;
import com.kr.lg.common.enums.Status2Enum;
import com.kr.lg.db.repositories.LawFirmRepository;
import com.kr.lg.model.common.global.GlobalCode;
import com.kr.lg.common.utils.LawFirmUtils;
import com.kr.lg.model.common.layer.LawFLayer;
import com.kr.lg.service.lawfirm.LawFirmUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LawFirmUpdateServiceImpl implements LawFirmUpdateService {

    private final LawFirmRepository lawFirmRepository;
    private final LawFirmUtils lawFirmUtils;

    @Override
    public void activeLawFirm(LawFLayer LawFLayer) throws LgException {
        LawFirmTb lawFirmTb = lawFirmRepository.findById(LawFLayer.getId()).orElseThrow(()->new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        lawFirmUtils.isLawFirmManager(lawFirmTb.getUserTb(), LawFLayer.getUserTb()); // 로펌 매니저 검사
        if (lawFirmTb.getStatus().equals(Status2Enum.DELETE_STATUS)) throw new LgException(GlobalCode.DELETE_LAW_FIRM);
        if (Status2Enum.STOP_STATUS.equals(lawFirmTb.getStatus())) { // 활성화
            lawFirmRepository.updateStatus(Status2Enum.NORMAL_STATUS, lawFirmTb.getLawFirmId());
        } else if (Status2Enum.NORMAL_STATUS.equals(lawFirmTb.getStatus())) { // 비활성화
            lawFirmRepository.updateStatus(Status2Enum.STOP_STATUS, lawFirmTb.getLawFirmId());
        }
    }

    @Override
    public void updateLawFirm(LawFLayer LawFLayer) throws LgException {
        LawFirmTb lawFirmTb = lawFirmRepository.findById(LawFLayer.getId()).orElseThrow(()->new LgException(GlobalCode.NOT_EXIST_LAW_FIRM));
        lawFirmUtils.isLawFirmManager(lawFirmTb.getUserTb(), LawFLayer.getUserTb()); // 로펌 매니저 검사
        if (lawFirmTb.getStatus().equals(Status2Enum.DELETE_STATUS)) throw new LgException(GlobalCode.DELETE_LAW_FIRM);
        if (!StringUtils.isBlank(LawFLayer.getIntroduction())) {
            lawFirmRepository.updateLawFirm(lawFirmUtils.getImageUrl(LawFLayer.getProfile()), lawFirmUtils.getImageUrl(LawFLayer.getBackground()), LawFLayer.getIntroduction(), lawFirmTb.getLawFirmId());
        } else {
            lawFirmRepository.updateLawFirm(lawFirmUtils.getImageUrl(LawFLayer.getProfile()), lawFirmUtils.getImageUrl(LawFLayer.getBackground()), lawFirmTb.getLawFirmId());
        }
    }
}
