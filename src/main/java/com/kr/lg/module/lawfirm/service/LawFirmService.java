package com.kr.lg.module.lawfirm.service;

import com.kr.lg.db.entities.UserTb;
import com.kr.lg.module.lawfirm.model.req.ApplyLawFirmRequest;
import com.kr.lg.module.lawfirm.exception.LawFirmException;
import com.kr.lg.module.lawfirm.model.req.CancelApplyLawFirmRequest;
import com.kr.lg.module.lawfirm.model.req.QuitLawFirmRequest;

public interface LawFirmService {

    void applyLawFirm(ApplyLawFirmRequest request, UserTb userTb) throws LawFirmException;
    void quitLawFirm(QuitLawFirmRequest request, UserTb userTb) throws LawFirmException;
    void cancelApplyLawFirm(CancelApplyLawFirmRequest request, UserTb userTb) throws LawFirmException;
}
