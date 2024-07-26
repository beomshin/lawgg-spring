package com.kr.lg.module.lawfirm.service;

import com.kr.lg.module.lawfirm.exception.LawFirmException;

public interface LawFirmDeleteService {

    void quitLawFirm(long userId) throws LawFirmException;
    void cancelApplyLawFirm(long lawFirmId, long userId) throws LawFirmException;
}
