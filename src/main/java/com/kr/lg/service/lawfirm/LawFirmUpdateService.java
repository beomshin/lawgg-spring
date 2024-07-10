package com.kr.lg.service.lawfirm;

import com.kr.lg.exception.LgException;
import com.kr.lg.model.common.layer.LawFLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface LawFirmUpdateService {

    @Transactional(rollbackFor = Exception.class)
    void activeLawFirm(LawFLayer LawFLayer) throws LgException;

    @Transactional(rollbackFor = Exception.class)
    void updateLawFirm(LawFLayer LawFLayer) throws LgException;
}
