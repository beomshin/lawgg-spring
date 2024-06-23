package com.kr.lg.service.lawfirm;

import com.kr.lg.exception.LgException;
import com.kr.lg.web.common.layer.LawFLayer;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface LawFirmDeleteService {

    @Transactional
    void deleteLawFirm(LawFLayer LawFLayer) throws LgException;
    @Transactional
    int quitLawFirm(LawFLayer LawFLayer) throws LgException;
    @Transactional
    void userDeleteLawFirm(LawFLayer LawFLayer) throws LgException;
    @Transactional
    int userCancelLawFirm(LawFLayer LawFLayer) throws LgException;
}
